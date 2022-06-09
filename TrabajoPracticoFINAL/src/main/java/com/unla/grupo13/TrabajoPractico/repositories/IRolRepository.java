package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.UserRole;


@Repository("rolRepository")
public interface IRolRepository extends JpaRepository<UserRole, Serializable> {

public abstract UserRole findByRole(String role);
public abstract UserRole findById(int id);
}
