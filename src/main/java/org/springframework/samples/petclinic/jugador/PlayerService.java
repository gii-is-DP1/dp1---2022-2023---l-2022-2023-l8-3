package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {
	@Autowired
	private PlayerRepository playerRepo;
	
	@Transactional
	public int playerCount() {
		return (int)	playerRepo.count();
	}
	
	@Transactional(readOnly = true)
	public Jugador findJugadorById(int id) throws DataAccessException {
		return playerRepo.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Jugador> findAllJugadores() throws DataAccessException {
		return playerRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(String lastName) throws DataAccessException {
		return playerRepo.findByLastName(lastName);
	}
	
	
}
