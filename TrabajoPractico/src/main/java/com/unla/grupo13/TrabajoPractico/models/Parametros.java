package com.unla.grupo13.TrabajoPractico.models;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;

public class Parametros {

    //private Aula aula;
   
	private int cantPc;
	private int cantSillas;
    private char turno;
	private int diaSemana;
    
    public int getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(int diaSemana) {
		this.diaSemana = diaSemana;
	}
	public int getCantPc() {
		return cantPc;
	}
	public void setCantPc(int cantPc) {
		this.cantPc = cantPc;
	}
	public int getCantSillas() {
		return cantSillas;
	}
	public void setCantSillas(int cantSillas) {
		this.cantSillas = cantSillas;
	}
	public char getTurno() {
		return turno;
	}
	public void setTurno(char turno) {
		this.turno = turno;
	}

    
}