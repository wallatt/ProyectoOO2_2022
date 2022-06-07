package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;

public interface IAulaService {

	
	public List<Aula> getAll();
	public List<Laboratorio> getByEdificio(int id);
	public List<Tradicional> getByEdificio2(int id);
	public List<Tradicional> findEspaciosTrad(char turno, boolean b, int cantEstudiantes, boolean tieneProyector);
	public Aula getById(int id_aula);
	List<Laboratorio> findEspaciosLab(char turno, boolean libre, int cantEstudiantes, int cantPc);
}
