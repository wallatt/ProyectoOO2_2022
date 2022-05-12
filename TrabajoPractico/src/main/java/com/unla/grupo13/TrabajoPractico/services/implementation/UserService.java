package com.unla.grupo13.TrabajoPractico.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.User;
import com.unla.grupo13.TrabajoPractico.models.UserModel;
import com.unla.grupo13.TrabajoPractico.repositories.IUserRepository;
import com.unla.grupo13.TrabajoPractico.services.IUserService;



@Service("userService")
public class UserService implements IUserService{

	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	private ModelMapper modelMapper=new ModelMapper();
	
	
	@Override
	
	
	public UserModel save(User user) {
		// TODO Auto-generated method stub
	
		User nuevoU = userRepository.save(user);
		return modelMapper.map(nuevoU,UserModel.class);
	}


}
