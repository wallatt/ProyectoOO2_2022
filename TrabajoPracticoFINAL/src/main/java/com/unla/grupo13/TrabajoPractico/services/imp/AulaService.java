package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;
import com.unla.grupo13.TrabajoPractico.repositories.IAulaRepository;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;

@Service("aulaService")
public class AulaService implements IAulaService {

	@Autowired
	@Qualifier("aulaRepository")
	private IAulaRepository aulaRepository;

	@Override
	public List<Aula> getAll() {
		// TODO Auto-generated method stub
		return aulaRepository.findAll();
	}

	@Override
	public List<Laboratorio> getByEdificio(int id) {
		// TODO Auto-generated method stub
		return aulaRepository.findByEdificio(id);
	}

	@Override
	public List<Tradicional> getByEdificio2(int id) {
		// TODO Auto-generated method stub
		return aulaRepository.findByEdificio2(id);
	}

	@Override
	public List<Laboratorio> findAllLaboratorio() {
		return aulaRepository.findAllLaboratorios();
	}

	@Override
	public List<Tradicional> findAllTradicional() {
		return aulaRepository.findAllTradicional();
	}

}
