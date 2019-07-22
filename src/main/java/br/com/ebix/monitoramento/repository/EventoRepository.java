package br.com.ebix.monitoramento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.ebix.monitoramento.model.Evento;

@Repository
public interface EventoRepository  extends JpaRepository<Evento, Long> {

	@Query("select e from Evento e where e.nmEmail=:nmEmail")
	public Evento buscarPorEmail(@Param("nmEmail") String nmEmail);
	
	@Query("select e from Evento e")
	public List<Evento> buscarTodos(); 
	
	@Query("select e from Evento e where e.id=:id")
	public Evento buscarPorUrl(@Param("id") Evento id);
	
	@Query("select e from Evento e")
	public List<Evento> listAll();
	
	
	
}
