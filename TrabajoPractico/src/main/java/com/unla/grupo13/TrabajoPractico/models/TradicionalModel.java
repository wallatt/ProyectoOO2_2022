package com.unla.grupo13.TrabajoPractico.models;

import javax.persistence.Column;

public class TradicionalModel {

	
	
	private int id;
	
	private int cantBancos;

    private String pizarron;

 
    private boolean tieneProyector;

    
    public TradicionalModel() {}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
    
    
    
}
