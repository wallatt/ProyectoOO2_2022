package com.unla.grupo13.TrabajoPractico.models;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;

public class Parametros {

    private int aulaId;
    private LocalDate fechaFinal;
    private int diaSemana;
    //0 = full; 1=50; 2=final
    private int tipopresencial;
    private char turnoMateria;
    private int numAsientos;
    private boolean tipoAula;



    public Parametros() {
    }

    public int getAulaId() {
        return aulaId;
    }

    public void setAulaId(int aulaId) {
        this.aulaId = aulaId;
    }

    public boolean isTipoAula() {
        return tipoAula;
    }
//    public void setFechaFinal(LocalDate fechaFinal) {
//        this.fechaFinal = fechaFinal;
//    }

    public boolean esLaboratorio() {
        return tipoAula;
    }

    public void setTipoAula(boolean tipoAula) {
        this.tipoAula = tipoAula;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {

        this.fechaFinal = LocalDate.parse(fechaFinal);
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

    public int getNumAsientos() {
        return numAsientos;
    }

    public void setNumAsientos(int numAsientos) {
        this.numAsientos = numAsientos;
    }

    @Override
    public String toString() {
        return "Parametros{" +
                "aulaId=" + aulaId +
                ", fechaFinal=" + fechaFinal +
                ", diaSemana=" + diaSemana +
                ", tipopresencial=" + tipopresencial +
                ", turnoMateria=" + String.valueOf(turnoMateria) +
                ", numAsientos=" + numAsientos +
                ", tipoAula=" + tipoAula +
                '}';
    }
}
