package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDate;


import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Espacio extends EntityBase {
	
	public Espacio(LocalDate fecha2, char turno2, Aula aula2, boolean libre2) {
		// TODO Auto-generated constructor stub
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


    public Espacio(Aula aula, char turno, LocalDate fecha, boolean libre) {
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
