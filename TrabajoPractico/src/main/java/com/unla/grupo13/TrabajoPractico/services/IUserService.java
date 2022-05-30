package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.User;
import com.unla.grupo13.TrabajoPractico.models.UserModel;

public interface IUserService {

	
	
	public UserModel save(User user);
	public UserModel findByUserName(String userName);
	public User findById(int id);
	public List<User> getAll(String userName);
	
	
	
}
