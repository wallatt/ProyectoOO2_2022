package com.unla.grupo13.TrabajoPractico.models;

public class TradicionalUploadModel {

    private int id;
    private char turno;
    private boolean libre;
    private int cantEstudiantes;
    private boolean tieneProyector;
    private int diaSemana;

    private int disponibidad;

    private int notaPedido;

    public TradicionalUploadModel() {}


    public int getDiaSemana() {
        return diaSemana;
    }


    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

    public int getCantEstudiantes() {
        return cantEstudiantes;
    }

    public void setCantEstudiantes(int cantEstudiantes) {
        this.cantEstudiantes = cantEstudiantes;
    }

    public int getDisponibidad() {
        return disponibidad;
    }

    public void setDisponibidad(int disponibidad) {
        this.disponibidad = disponibidad;
    }

    public boolean isTieneProyector() {
        return tieneProyector;
    }

    public void setTieneProyector(boolean tieneProyector) {
        this.tieneProyector = tieneProyector;
    }

    public int getNotaPedido() {
        return notaPedido;
    }

    public void setNotaPedido(int notaPedido) {
        this.notaPedido = notaPedido;
    }
}
