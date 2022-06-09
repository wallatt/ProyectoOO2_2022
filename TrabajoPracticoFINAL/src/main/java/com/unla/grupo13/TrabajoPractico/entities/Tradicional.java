package com.unla.grupo13.TrabajoPractico.entities;



import javax.persistence.Column;
import javax.persistence.Entity;



@Entity
public class Tradicional extends Aula {

	@Column(name = "cantBancos")
	private int cantBancos;

	@Column(name = "pizarron")
	private String pizarron;

	@Column(name = "tieneproyector")
	private boolean tieneProyector;

	public Tradicional() {
	}

	public int getCantBancos() {
		return cantBancos;
	}

	public void setCantBancos(int cantBancos) {
		this.cantBancos = cantBancos;
	}

	public String getPizarron() {
		return pizarron;
	}

	public void setPizarron(String pizarron) {
		this.pizarron = pizarron;
	}

	public boolean isTieneProyector() {
		return tieneProyector;
	}

	public void setTieneProyector(boolean tieneProyector) {
		this.tieneProyector = tieneProyector;
	}

	@Override
	public String toString() {
		return "Tradicional{" +
				"id=" + id +
				", softDelete=" + softDelete +
				", fechaCreacion=" + fechaCreacion +
				", fechaModificacion=" + fechaModificacion +
				", numero=" + numero +
				//", edificio=" + edificio +
				", cantBancos=" + cantBancos +
				", pizarron='" + pizarron + '\'' +
				", tieneProyector=" + tieneProyector +
				'}';
	}
}
