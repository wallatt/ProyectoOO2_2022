package com.unla.grupo13.TrabajoPractico.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


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

   
	@Column(name = "numero")
    protected int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edificio_id", nullable = false)
    protected Edificio edificio;

    
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



	@Override
	public String toString() {
		return "Aula{" +
				"id=" + id +
				", softDelete=" + softDelete +
				", fechaCreacion=" + fechaCreacion +
				", fechaModificacion=" + fechaModificacion +
				", numero=" + numero +
				", edificio=" + edificio +
				'}';
	}
}
