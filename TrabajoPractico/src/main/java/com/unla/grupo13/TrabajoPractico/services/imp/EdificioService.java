package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.Edificio;
import com.unla.grupo13.TrabajoPractico.repositories.IEdificioRepository;
import com.unla.grupo13.TrabajoPractico.services.IEdificioService;


@Service("edificioService")
public class EdificioService implements IEdificioService{

	
	
	@Autowired
	@Qualifier("edificioRepository")
	private IEdificioRepository edificioRepository;

	@Override
	public List<Edificio> getAll() {
		// TODO Auto-generated method stub
		return edificioRepository.findAll();
	}
}
