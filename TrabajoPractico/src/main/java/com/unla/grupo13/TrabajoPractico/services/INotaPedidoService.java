package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;
import java.util.Set;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.models.EspacioModel;
import com.unla.grupo13.TrabajoPractico.models.NotaPedidoModel;

public interface INotaPedidoService {

	
	
	public NotaPedido save(NotaPedido notaPedido);
	public List<NotaPedido> getAll();

	public NotaPedido asignarEspacios(NotaPedido notaPedido, Set<Espacio> espacios);

	public NotaPedido get(int Id);
}
