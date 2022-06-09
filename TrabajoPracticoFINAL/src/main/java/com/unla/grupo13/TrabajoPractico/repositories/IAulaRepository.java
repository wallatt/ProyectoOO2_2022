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
public interface IAulaRepository extends JpaRepository<Aula, Integer> {

	
	@Query("SELECT a FROM Aula a")
	public abstract List<Aula> findAll();

	@Query("SELECT l FROM Laboratorio l JOIN FETCH l.edificio e WHERE e.id=(:id)")
	public abstract List<Laboratorio> findByEdificio(int id);

	@Query("SELECT t FROM Tradicional t JOIN FETCH t.edificio e WHERE e.id=(:id)")
	public abstract List<Tradicional> findByEdificio2(int id);

	@Query("SELECT l from Laboratorio l")
	public abstract List<Laboratorio> findAllLaboratorios();

	@Query("SELECT t from Tradicional t")
	public abstract List<Tradicional> findAllTradicional();
}
