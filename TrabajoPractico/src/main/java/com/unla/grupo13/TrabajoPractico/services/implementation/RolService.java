package com.unla.grupo13.TrabajoPractico.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.unla.grupo13.TrabajoPractico.entities.UserRole;
import com.unla.grupo13.TrabajoPractico.repositories.IRolRepository;
import com.unla.grupo13.TrabajoPractico.services.IRolService;

public class RolService implements IRolService{

	@Autowired
	@Qualifier("rolRepository")
	private IRolRepository rolRepository;
	
	@Override
	public UserRole findByRole(String role) {
		return rolRepository.findByRole(role);
	}

	@Override
	public UserRole findById(int id) {
		// TODO Auto-generated method stub
		return rolRepository.findById(id);
	}

}
