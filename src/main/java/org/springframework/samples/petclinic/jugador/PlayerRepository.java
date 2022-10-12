package org.springframework.samples.petclinic.jugador;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.Owner;

public interface PlayerRepository extends CrudRepository<Jugador, Integer>{
	
	@Query("SELECT jugador FROM Jugador jugador")
	public List<Jugador> findAll() throws DataAccessException;
	
	
	@Query("SELECT jugador FROM Jugador jugador WHERE jugador.id =:id")
	public Jugador findById(@Param("id") int id);
	
	@Query("SELECT DISTINCT jugador FROM Jugador jugador WHERE jugador.lastName LIKE :lastName%")
	public Collection<Jugador> findByLastName(@Param("lastName") String lastName);

}
