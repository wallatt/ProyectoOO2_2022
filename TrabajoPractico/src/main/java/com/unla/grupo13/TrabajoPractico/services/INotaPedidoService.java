package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;

public interface INotaPedidoService {

	
	
	public NotaPedido save(NotaPedido notaPedido);
	public List<NotaPedido> getAll();
}
