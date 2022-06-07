package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.models.NotaPedidoModel;

public interface INotaPedidoService {

	
	
	public List<NotaPedido> getAll();
	
	public NotaPedidoModel save(NotaPedido notaPedido);
	public NotaPedido get(int id_pedido);
	
}
