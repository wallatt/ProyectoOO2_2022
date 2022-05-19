package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;

public class Espacio extends EntityBase {
	
	@Column
	protected LocalDate fecha;
	
	@Column
	protected char turno;
	
	@Column
	protected Aula aula;
	
	@Column
	protected boolean libre;

	public Espacio(LocalDate fecha,char turno,Aula aula,boolean libre,boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
		super(softDelete, fechaCreacion, fechaModificacion);
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.libre = libre;
	}

}
