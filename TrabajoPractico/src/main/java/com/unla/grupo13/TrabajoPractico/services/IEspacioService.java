package com.unla.grupo13.TrabajoPractico.services;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;

public interface IEspacioService {




    public Espacio generarEspacios(Aula aula, char turno, LocalDate fecha, boolean libre) throws Exception;
    public void generarEspacioMes(int anio, int mes, char turno, Aula aula) throws Exception;



}