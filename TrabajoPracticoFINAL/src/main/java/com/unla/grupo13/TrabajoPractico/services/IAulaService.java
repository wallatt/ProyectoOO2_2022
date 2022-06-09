package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;

public interface IAulaService {

	public List<Aula> getAll();

	public List<Laboratorio> getByEdificio(int id);

	public List<Tradicional> getByEdificio2(int id);

	public List<Laboratorio> findAllLaboratorio();

	public List<Tradicional> findAllTradicional();
}
