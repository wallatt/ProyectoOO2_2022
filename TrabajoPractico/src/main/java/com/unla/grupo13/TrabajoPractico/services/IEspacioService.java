package com.unla.grupo13.TrabajoPractico.services;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.LocalDate;
import java.util.List;

public interface IEspacioService {

	public Espacio generarEspacios(Aula aula, char turno, LocalDate fecha, boolean libre) throws Exception;

	public void generarEspacioMes(String fechaInicio, String fechaFinalizacion, char turno, Aula aula) throws Exception;

	public List<Espacio> getByTurno(char turno);

	public List<Espacio> getByAulaId(int id_aula);

	public List<Espacio> getByTurno1(char turno);

	public List<Espacio> traerEspaciosDeAula(Aula aula, char turno);
	
	public List<Espacio> traerEspacioDia(int diaSemana,List<Espacio> espacios);
	
	public Espacio getById(int id);
	public void save(Espacio espacio);
}