package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;

@Repository("aulaRepository")
public interface IAulaRepository extends JpaRepository<Aula,Serializable>{


	public abstract List<Aula> findAll();
	
	@Query("SELECT l FROM Laboratorio l JOIN FETCH l.edificio e WHERE e.id=(:id)")
	public abstract List<Laboratorio>findByEdificio(int id);
	
	@Query("SELECT t FROM Tradicional t JOIN FETCH t.edificio e WHERE e.id=(:id)")
	public abstract List<Tradicional>findByEdificio2(int id);

	@Query("SELECT t FROM Tradicional t JOIN FETCH t.espacios e WHERE t.tieneProyector=(:proyector) and t.cantBancos>=(:cantEstudiantes) and e.libre=(:libre) and e.turno=(:turno) and t.porcetanjeDisponibilidad>=(:porcetanjeDisponibilidad) GROUP BY t.id")
	public abstract List<Tradicional> findEspaciosTrad(char turno,boolean libre,int cantEstudiantes,boolean proyector, int porcetanjeDisponibilidad);

	@Query("SELECT l FROM Laboratorio l JOIN FETCH l.espacios e WHERE l.cantSillas>=(:cantEstudiantes) and l.cantPc>=(:cantPc) and e.libre=(:libre) and e.turno=(:turno) GROUP BY l.id")
	public abstract List<Laboratorio> findEspaciosLab(char turno,boolean libre,int cantEstudiantes,int cantPc);

	public abstract Aula findById(int id);

	@Query("SELECT t FROM Tradicional t JOIN FETCH t.espacios e WHERE e.libre=(:libre) and e.turno=(:turno) and t.id=(:id)")
	public abstract Tradicional findTradicionalWithEspacios(boolean libre, char turno, int id);
}
