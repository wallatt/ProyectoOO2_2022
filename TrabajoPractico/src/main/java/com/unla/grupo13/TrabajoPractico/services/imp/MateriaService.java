package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.Materia;
import com.unla.grupo13.TrabajoPractico.repositories.IMateriaRepository;
import com.unla.grupo13.TrabajoPractico.services.IMateriaService;

@Service("materiaService")
public class MateriaService implements IMateriaService{

	@Autowired
	@Qualifier("materiaRepository")
	private IMateriaRepository materiaRepository;
	
	@Override
	public Materia getByNombre(String nombre) {
		// TODO Auto-generated method stub
		return materiaRepository.findByNombre(nombre);
	}

	@Override
	public List<Materia> getAll() {
		// TODO Auto-generated method stub
		return materiaRepository.findAll();
	}

}
