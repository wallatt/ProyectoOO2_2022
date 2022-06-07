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
public class AulaService implements IAulaService{

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
	public Aula getById(int id) {
		// TODO Auto-generated method stub
		return aulaRepository.findById(id);
	}

	@Override
	public List <Laboratorio> findEspaciosLab(char turno, boolean libre, int cantEstudiantes, int cantPc) {
		
		
	
		// TODO Auto-generated method stub
		return aulaRepository.findEspaciosLab(turno, libre, cantEstudiantes, cantPc);
	}

	@Override
	public List <Tradicional> findEspaciosTrad(char turno, boolean libre, int cantEstudiantes, boolean proyector) {
		// TODO Auto-generated method stub
		return aulaRepository.findEspaciosTrad(turno, libre, cantEstudiantes, proyector);
	}

	
	
}