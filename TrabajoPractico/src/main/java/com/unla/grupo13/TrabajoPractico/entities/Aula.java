package com.unla.grupo13.TrabajoPractico.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Aula{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;

	@Column(name = "softDelete")
	protected boolean softDelete = Boolean.TRUE;

	@Column(name = "fechaCreacion")
	@CreationTimestamp
	protected LocalDateTime fechaCreacion;

	@Column(name = "fechaModificacion")
	@UpdateTimestamp
	protected LocalDateTime fechaModificacion;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="aula")
	protected Set<Espacio> espacios=new HashSet<Espacio>();
	
	@Column(name = "numero")
    protected int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edificio_id", nullable = false)
    protected Edificio edificio;

	@Column(name = "disponibilidad")
	protected int porcetanjeDisponibilidad;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(boolean softDelete) {
		this.softDelete = softDelete;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

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
				", porcetanjeDisponibilidad=" + porcetanjeDisponibilidad +
				", id=" + id +
				", softDelete=" + softDelete +
				", fechaCreacion=" + fechaCreacion +
				", fechaModificacion=" + fechaModificacion +
				'}';
	}
}
