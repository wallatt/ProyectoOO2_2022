package com.unla.grupo13.TrabajoPractico.models;

import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.util.List;

public class LaboratorioModel {

	
	private int id;
	
	private int cantSillas;
	
	private int cantPc;
	
	public LaboratorioModel() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantSillas() {
		return cantSillas;
	}

	public void setCantSillas(int cantSillas) {
		this.cantSillas = cantSillas;
	}

	public int getCantPc() {
		return cantPc;
	}

	public void setCantPc(int cantPc) {
		this.cantPc = cantPc;
	}

	private List<Espacio> espacios;

	public List<Espacio> getEspacios() {
		return espacios;
	}

	public void setEspacios(List<Espacio> espacios) {
		this.espacios = espacios;
	}
	
}
