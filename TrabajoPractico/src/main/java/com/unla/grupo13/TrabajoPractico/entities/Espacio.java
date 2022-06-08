package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDate;


import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Espacio extends EntityBase {
	
	@Column(name="fecha")
	private LocalDate fecha;
	
	@Column(name="turno")
	private char turno;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aula_id", nullable = true)
	private Aula aula;
	
	@Column(name="libre")
	private boolean libre;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notapedido_id", nullable = true)
	private NotaPedido notaPedido;

	public Espacio(Aula aula, char turno, LocalDate fecha, boolean libre) {
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.libre = libre;
	}

	public Espacio() {}



	public LocalDate getFecha() {
		return fecha;
	}


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}


	public char getTurno() {
		return turno;
	}


	public void setTurno(char turno) {
		this.turno = turno;
	}


	public Aula getAula() {
		return aula;
	}


	public void setAula(Aula aula) {
		this.aula = aula;
	}


	public boolean isLibre() {
		return libre;
	}


	public void setLibre(boolean libre) {
		this.libre = libre;
	}


	public NotaPedido getNotaPedido() {
		return notaPedido;
	}


	public void setNotaPedido(NotaPedido notaPedido) {
		this.notaPedido = notaPedido;
	}
    

}
