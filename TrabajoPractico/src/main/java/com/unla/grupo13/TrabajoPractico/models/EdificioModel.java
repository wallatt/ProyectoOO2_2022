package com.unla.grupo13.TrabajoPractico.models;


import com.unla.grupo13.TrabajoPractico.entities.Aula;

import java.util.List;

public class EdificioModel {

	
	
	private int id;
	private String edificio;
	private List<Aula> aula;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEdificio() {
		return edificio;
	}
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	public List<Aula> getAulaModel() {
		return aula;
	}

	public void setAulaModel(List<Aula> aulaModel) {
		this.aula = aula;
	}

	public EdificioModel() {}
	
	
}
