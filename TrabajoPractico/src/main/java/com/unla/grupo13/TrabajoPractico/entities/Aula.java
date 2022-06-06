package com.unla.grupo13.TrabajoPractico.entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Aula extends EntityBase{

   
	@Column(name = "numero")
    protected int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edificio_id", nullable = false)
    protected Edificio edificio;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="aula")
	protected Set<Espacio> espacios=new HashSet<Espacio>();

	@Column(name = "disponibilidad")
	protected int porcetanjeDisponibilidad;
    
    public Aula() {}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public Edificio getEdificio() {
		return edificio;
	}


	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public int getPorcetanjeDisponibilidad() {
		return porcetanjeDisponibilidad;
	}

	public void setPorcetanjeDisponibilidad(int porcetanjeDisponibilidad) {
		this.porcetanjeDisponibilidad = porcetanjeDisponibilidad;
	}

	public Set<Espacio> getEspacios() {
		return espacios;
	}

	public void setEspacios(Set<Espacio> espacios) {
		this.espacios = espacios;
	}

	@Override
	public String toString() {
		return "Aula{" +
				"numero=" + numero +
				//", edificio=" + edificio +
				//", espacios=" + espacios +
				", porcetanjeDisponibilidad=" + porcetanjeDisponibilidad +
				", id=" + id +
				'}';
	}
}
