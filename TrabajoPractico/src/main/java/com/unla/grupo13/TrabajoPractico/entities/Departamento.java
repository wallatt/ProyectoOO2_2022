package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
@Entity
@Table(name = "departamento")
public class Departamento extends EntityBase{

	@Column(name="nombre")
	private String departamento;
	
	
	public Departamento(boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion, String departamento) {
		super(softDelete, fechaCreacion, fechaModificacion);
		// TODO Auto-generated constructor stub
		this.departamento=departamento;
	}

}
