package com.unla.grupo13.TrabajoPractico.models;

import com.unla.grupo13.TrabajoPractico.entities.Aula;

public class EspacioModel {


    private int id;
    private int anio;
    private int mes;
    private char turno;
    private Aula aula;

    public EspacioModel() {}

    public EspacioModel(int anio, int mes, char turno, Aula aula) {
        super();
        this.anio = anio;
        this.mes = mes;
        this.turno = turno;
        this.aula = aula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }



}