package com.unla.grupo13.TrabajoPractico.models;

public class LaboratorioUploadModel {

    private int id;
    private int cantPc;
    private int cantSillas;
    private char turno;
    private int diaSemana;
    private int disponibidad;
    public int getDisponibidad() {
        return disponibidad;
    }

    public void setDisponibidad(int disponibidad) {
        this.disponibidad = disponibidad;
    }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
