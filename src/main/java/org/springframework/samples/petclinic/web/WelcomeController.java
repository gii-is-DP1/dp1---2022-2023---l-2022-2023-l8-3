package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.invitacion.Invitacion;
import org.springframework.samples.petclinic.invitacion.InvitationService;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.samples.petclinic.jugador.PlayerService;
import org.springframework.samples.petclinic.menu.MenuService;
import org.springframework.samples.petclinic.partida.Match;
import org.springframework.samples.petclinic.partida.MatchService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	private InvitationService invitacionService;
	private MenuService menuService;
	private PlayerService playerService;
	private MatchService matchService;
	
	@Autowired
	public WelcomeController(InvitationService invitacionService,MenuService menuService,PlayerService playerService,MatchService matchService) {
		this.playerService = playerService;
		this.menuService=menuService;
		this.invitacionService=invitacionService;
		this.matchService=matchService;
		
	}

	@GetMapping("/")
	  public ModelAndView welcome() {	    
		ModelAndView result=new ModelAndView("welcome");
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		System.out.println("error2");
		User user1=new User("testUser1","testUser1");
		Set<Authorities> conj=new HashSet<Authorities>();
		Authorities authority=new Authorities(); authority.setUser(user1);authority.setAuthority("admin");
		conj.add(authority);
    	user1.setAuthorities(conj);
		
		if(auth!=null) {
			Boolean b=true;
			for(Authorities a:menuService.findUser(auth.getName()).orElse(user1).getAuthorities()) {
				if(a.getAuthority().equals("admin")) {
					b=false;
				}
			}
			if(b) {
				Jugador jugadorActual=menuService.findPlayerByUsername(auth.getName());
				Collection<Match> partidas = matchService.getMatches();
				for(Match partida:partidas) {
				    if(partida.getFinPartida()==null&&(partida.getJugador1()==jugadorActual||partida.getJugador2()==jugadorActual)) {       
				    	int id = partida.getId();
				        result.addObject("matchId", id);
				        if(partida.getJugador2()!=null) {
				        	result.addObject("partidaPendiente", true);
				        }
				        else {
				        	result.addObject("jugador2NoUnido",true);
				        }
				    }
				}
				
				List<Invitacion> lista=invitacionService.getInvitacionByInvitadoId(jugadorActual.getId());
				if(lista.isEmpty()) {
					result.addObject("tengoInvitaciones",false);
				}
				else {
				result.addObject("tengoInvitaciones",true);
				result.addObject("mensaje","Ha recibido una invitaci&oacute;n a una partida, acceda a trav&eacute;s de la campana del menu");
				}
			}
		}
		else {
			result.addObject("tengoInvitaciones",false);
			result.addObject("mensaje","este mensaje no vale para nada");
		}
		return result;
	}
	
	@GetMapping(value = "/registerNewJugador")
	public String getRegisterNewPlayer(Map<String, Object> model, Map<String, Object> model2) {
		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		Collection<Jugador> c = this.playerService.findAllJugadores();
		if (c.isEmpty()) {
			model2.put("sinJugadores", true);
		}
		return "jugadores/createOrUpdateJugadorForm";
	}

	@PostMapping(value = "/registerNewJugador")
	public ModelAndView postRegisterNewPlayer(@Valid Jugador jugador, BindingResult br, Map<String, Object> model) {
		Boolean correctPassword = false;
		ModelAndView resul;
		
		if (br.hasErrors()) {
			resul = new ModelAndView("jugadores/createOrUpdateJugadorForm", br.getModel());
		} else {
			List<Jugador> lista = playerService.findAllJugadores();
			
			if(isRegisteredEmail(jugador, model, lista) || !isValidEmail(model, jugador) || !isCorrectPassword(jugador, model, correctPassword)) {
				resul = new ModelAndView("jugadores/createOrUpdateJugadorForm");
			} else {
				jugador.setEstadoOnline(false);
				this.playerService.saveJugador(jugador);
				resul = new ModelAndView("redirect:/login");
			}
		}
		return resul;
	}
	
	private Boolean isRegisteredEmail(Jugador jugador, Map<String, Object> model, List<Jugador> lista) {
		Boolean result = false;
		Integer i = 0;
		while(!result && i < lista.size()) {
			if (lista.get(i).getUser().getEmail().equals(jugador.getUser().getEmail())) {
				model.put("emailIncorrecto1", true);
				result = true;
			}
			i++;
		}
		return result;
	}
	
	private Boolean isValidEmail(Map<String, Object> model, Jugador player) {
		Boolean result = true;
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +"[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(player.getUser().getEmail());
		
		if (!matcher.matches()) {
			model.put("emailIncorrecto2", true);
			result = false;
		}
		
		return result;
	}

	private Boolean isCorrectPassword(Jugador player, Map<String, Object> model, Boolean correctPassword) {
		Integer i = 0;
		
		if(player.getUser().getPassword().length() >= 10 && player.getUser().getPassword().length() <= 50) {
			while(!correctPassword && i < 10) {
				if (player.getUser().getPassword().contains(i.toString())) {
					correctPassword = true;
				}
				i++;
			}
		}
		if(!correctPassword) {
			model.put("contraseñaIncorrecta", true);
		}
		
		return correctPassword;
	}
}
