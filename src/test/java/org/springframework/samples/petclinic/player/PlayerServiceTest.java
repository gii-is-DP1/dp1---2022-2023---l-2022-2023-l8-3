package org.springframework.samples.petclinic.player;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.PlayerService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerServiceTest {
	
	@Autowired
	private PlayerService playerService;
	
	@Test
	public void testCountWithInitialData() {
		int count = playerService.playerCount();
		assertEquals(count,1);
	}
	
}