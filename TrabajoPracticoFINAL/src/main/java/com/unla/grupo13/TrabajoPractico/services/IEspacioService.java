package com.unla.grupo13.TrabajoPractico.services;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;
import java.util.List;
public interface IEspacioService {
    public Espacio generarEspacios(Aula aula, char turno, LocalDate fecha, boolean libre) throws Exception;

   // public void generarEspacioMes(int anio, int mes, char turno, Aula aula) throws Exception;
    public void generarEspacioMes(String fechaInicio, String fechaFinalizacion, char turno, Aula aula) throws Exception;

    public void generarEspacioMes(String fechaInicio, String fechaFinalizacion, char turno) throws Exception;

    public List<Espacio> getByTurno(char turno);

    public List<Espacio> traerEspaciosDeAula(Aula aula , char turno, boolean libre);

    public List<Espacio> getByTurnoAndDiaSemana(char turno, int dia);

	// clases
	public List<Espacio> findByCodCurso(String codCurso);
	
	public List<Espacio> findAllAsignados();

}