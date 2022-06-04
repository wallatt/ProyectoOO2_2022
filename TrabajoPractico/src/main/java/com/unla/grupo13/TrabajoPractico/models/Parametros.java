package com.unla.grupo13.TrabajoPractico.models;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;

public class Parametros {

    //private Aula aula;
    private String fechaFinal;
    private int diaSemana;
    private int tipopresencial;
    private char turnoMateria;

    public Parametros() {
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getTipopresencial() {
        return tipopresencial;
    }

    public void setTipopresencial(int tipopresencial) {
        this.tipopresencial = tipopresencial;
    }

    public char getTurnoMateria() {
        return turnoMateria;
    }

    public void setTurnoMateria(char turnoMateria) {
        this.turnoMateria = turnoMateria;
    }
}
