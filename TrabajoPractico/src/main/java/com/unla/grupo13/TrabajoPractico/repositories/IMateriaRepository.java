package com.unla.grupo13.TrabajoPractico.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.Materia;

@Repository("materiaRepository")
public interface IMateriaRepository extends JpaRepository<Materia, Integer> {

	@Query("SELECT m FROM Materia m JOIN FETCH m.carrera WHERE m.nombre = (:nombre)")
	public abstract Materia findByNombre(@Param("nombre") String nombre);
	
}
