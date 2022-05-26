package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "espacio")
@Getter @Setter @NoArgsConstructor
public class Espacio extends EntityBase {
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@Column(name="turno")
	private char turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", nullable = false)
	private Aula aula;
	
	@Column(name="libre")
	private boolean libre;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notapedido_id", nullable = false)
	private NotaPedido notaPedido;
	

	public Espacio(LocalDate fecha,char turno,Aula aula,boolean libre,boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,NotaPedido notaPedido) {
		super(softDelete, fechaCreacion, fechaModificacion);
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.libre = libre;
		this.notaPedido=notaPedido;
	}

}
