package com.unla.grupo13.TrabajoPractico.services;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;
import java.util.List;

public interface IEspacioService {

    public Espacio generarEspacios(Aula aula, char turno, LocalDate fecha, boolean libre) throws Exception;
    public void generarEspacioMes(String fechaInicio, String fechaFinalizacion, char turno, Aula aula) throws Exception;

    //List<Espacio> getAllByConditions(boolean libre, char turno, int cantSillas);
}
