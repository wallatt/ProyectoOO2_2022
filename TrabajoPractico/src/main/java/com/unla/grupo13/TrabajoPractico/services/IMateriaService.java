package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Materia;

public interface IMateriaService {

	
	public Materia getByNombre(String nombre);
	public List<Materia> getAll();
}
