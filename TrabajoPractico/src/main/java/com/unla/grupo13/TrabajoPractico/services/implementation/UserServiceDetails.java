package com.unla.grupo13.TrabajoPractico.services.implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.UserRole;
import com.unla.grupo13.TrabajoPractico.repositories.IUserRepository;



@Service("userServiceDetails")
public class UserServiceDetails implements UserDetailsService {

	@Autowired
	@Qualifier("userRepository")
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.unla.grupo13.TrabajoPractico.entities.User user = userRepository.findByUsernameAndFetchUserRolesEagerly(username);
		return buildUser(user, buildGrantedAuthorities(user.getRole()));
	}
	
	private User buildUser(com.unla.grupo13.TrabajoPractico.entities.User user, List<GrantedAuthority> grantedAuthorities) {
		return new User(user.getUserName(), user.getPassword(), user.isEnabled(),
						true, true, true, //accountNonExpired, credentialsNonExpired, accountNonLocked,
						grantedAuthorities);
	}
	
	private List<GrantedAuthority> buildGrantedAuthorities(UserRole userRole2) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		
			grantedAuthorities.add(new SimpleGrantedAuthority(userRole2.getRole()));
		
		return new ArrayList<GrantedAuthority>(grantedAuthorities);
	}
	

}
