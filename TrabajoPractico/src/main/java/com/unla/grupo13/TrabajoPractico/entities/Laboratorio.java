package com.unla.grupo13.TrabajoPractico.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity

public class Laboratorio extends Aula{

    @Column(name = "cantpc")
    private int cantPc;

    @Column(name = "cantsillas")
    private int cantSillas;

    public Laboratorio() {}
    
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

    
    
}
