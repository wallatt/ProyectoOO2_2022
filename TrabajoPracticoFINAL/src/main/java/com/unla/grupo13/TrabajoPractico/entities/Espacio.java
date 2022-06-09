package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDate;


import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class Espacio extends EntityBase {
	
//	public Espacio(LocalDate fecha2, char turno2, Aula aula2, boolean libre2) {
		// TODO Auto-generated constructor stub
//	}
	public Espacio(){}

//	public Espacio(LocalDate fecha2, char turno2, Aula aula2, boolean libre2) {
//		this.fecha=fecha2;
//		this.aula=aula2;
//		this.turno=turno2;
//		this.libre=libre2;
//	}

	public Espacio(Aula aula, char turno, LocalDate fecha, boolean libre) {
		this.fecha = fecha;
		this.turno = turno;
		this.aula = aula;
		this.libre = libre;
	}

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
    @JoinColumn(name = "notapedido_id", nullable = true)
	private NotaPedido notaPedido;







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
    
    




	@Override
	public String toString() {
		return "Espacio{" +
				"fecha=" + fecha +
				", turno=" + turno +
				", aula=" + aula +
				", libre=" + libre +
				'}';
	}
}