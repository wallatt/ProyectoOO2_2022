package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unla.grupo13.TrabajoPractico.entities.Edificio;

@Repository("edificioRepository")
public interface IEdificioRepository extends JpaRepository<Edificio, Serializable> {

	
	

	
}
