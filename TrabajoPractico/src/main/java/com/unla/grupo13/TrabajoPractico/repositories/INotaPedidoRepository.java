package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;
import java.util.Set;

import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;

@Repository("notaPedidoRepository")
public interface INotaPedidoRepository extends JpaRepository<NotaPedido, Serializable>{

    public abstract NotaPedido findById(int id);


	
	
}
