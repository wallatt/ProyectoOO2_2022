package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.User;

@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Serializable> {

	@Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.userName = (:username)")
	public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String userName);
	
	public abstract User findById(int id);
	
	@Query ("SELECT u FROM User u JOIN FETCH u.role WHERE u.enabled=true")
	public abstract List<User> findAll();
	
	@Query("SELECT u FROM User u JOIN FETCH u.role WHERE u.userName= (:username) and u.enabled=true")
	public abstract List<User> findAll(@Param ("username")String userName);
}
