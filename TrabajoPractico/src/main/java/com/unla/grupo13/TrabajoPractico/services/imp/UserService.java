package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.User;
import com.unla.grupo13.TrabajoPractico.entities.UserRole;
import com.unla.grupo13.TrabajoPractico.models.UserModel;
import com.unla.grupo13.TrabajoPractico.repositories.IUserRepository;
import com.unla.grupo13.TrabajoPractico.services.IRolService;
import com.unla.grupo13.TrabajoPractico.services.IUserService;



@Service("userService")
public class UserService implements IUserService{

	@Autowired
	@Qualifier("rolService")
	private IRolService rolService;
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	private ModelMapper modelMapper=new ModelMapper();
	
	
	@Override
	public UserModel save(User user) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder(4);
		user.setPassword(pe.encode(user.getPassword()));
		User nuevoU = userRepository.save(user);
		return modelMapper.map(nuevoU,UserModel.class);
	}


	@Override
	public UserModel findByUserName(String userName) {
		User user=userRepository.findByUsernameAndFetchUserRolesEagerly(userName);
		return modelMapper.map(user, UserModel.class);
	}


	@Override
	public User findById(int id) {
		
		// TODO Auto-generated method stub
	
		return userRepository.findById(id);
	}


	
	@Override
	public List<User> getAll(String palabraClave) {
		// TODO Auto-generated method stub
		
		if (palabraClave!=null) {
			
			
			return userRepository.findAll(palabraClave);
		}
		
		
		return userRepository.findAll();
	}



	public User darBaja (int id) {


		User usuario = userRepository.findById(id);

		usuario.setEnabled(!usuario.isEnabled());
		return userRepository.save(modelMapper.map(usuario, User.class));
	}


	public User editar(User userModel,int id) {



		User usuario = userRepository.findById(id);
		User user = modelMapper.map(userModel, User.class);
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder(4);
		user.setPassword(pe.encode(user.getPassword()));
		usuario.setId(user.getId());
		usuario.setNombre(user.getNombre());
		usuario.setApellido(user.getApellido());
		usuario.setUserName(user.getUserName());
		usuario.setDni(user.getDni());
		usuario.setEmail(user.getEmail());
		usuario.setPassword(user.getPassword());
		return userRepository.save(modelMapper.map(usuario, User.class));

	}

	
	
	


}
