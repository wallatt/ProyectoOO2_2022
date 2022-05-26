package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
public class Materia extends EntityBase {

	@Column(name="codmateria")
	private String codMateria;
	
	@Column (name="nombre")
	private String nombre;
	
	@Column (name="codComision")
	private String codComision;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrera_id", nullable = false)
	private Carrera carrera;
	
	public Materia(boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,String codMateria,String nombre,Carrera carrera,String codComision) {
		super(softDelete, fechaCreacion, fechaModificacion);
		// TODO Auto-generated constructor stub
		
		this.codMateria=codMateria;
		this.nombre=nombre;
		this.carrera=carrera;
		this.codComision=codComision;
	}

	
	
	
	
	
}
