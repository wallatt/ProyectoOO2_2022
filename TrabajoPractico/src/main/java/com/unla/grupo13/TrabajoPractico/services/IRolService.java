package com.unla.grupo13.TrabajoPractico.services;

import com.unla.grupo13.TrabajoPractico.entities.UserRole;

public interface IRolService {

	
	public UserRole findByRole(String role);
	public UserRole findById(int id);
}
