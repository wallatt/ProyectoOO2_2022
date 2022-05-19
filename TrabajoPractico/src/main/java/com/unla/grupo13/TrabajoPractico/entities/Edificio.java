package com.unla.grupo13.TrabajoPractico.entities;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Edificio extends EntityBase {
	
	@Column
	protected String edificio;
	
	@OneToMany(mappedBy="aula")
	protected Set<Aula> aulas;
	

	public Edificio(String edificio,boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
		super(softDelete, fechaCreacion, fechaModificacion);
		this.edificio = edificio;
	}

	
	
}
