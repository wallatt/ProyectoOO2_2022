package com.unla.grupo13.TrabajoPractico.entities;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Edificio extends EntityBase {
	
	@Column(name="edificio")
	private String edificio;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="edificio")
	private Set<Aula> aulas=new HashSet<Aula>();
	

	public Edificio(String edificio,boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
		super(softDelete, fechaCreacion, fechaModificacion);
		this.edificio = edificio;
	}

	
	
}
