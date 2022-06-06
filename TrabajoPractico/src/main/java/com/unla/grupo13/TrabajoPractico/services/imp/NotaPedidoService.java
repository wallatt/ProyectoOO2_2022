package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;
import java.util.Set;

import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.repositories.IMateriaRepository;
import com.unla.grupo13.TrabajoPractico.repositories.INotaPedidoRepository;
import com.unla.grupo13.TrabajoPractico.services.IMateriaService;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;


@Service("notaPedidoService")
public class NotaPedidoService implements INotaPedidoService{

	@Autowired
	@Qualifier("notaPedidoRepository")
	private INotaPedidoRepository notaPedidoRepository;
	
	@Autowired
	@Qualifier("materiaService")
	private IMateriaService materiaService;
	
	@Override
	public NotaPedido save(NotaPedido notaPedido) {
		// TODO Auto-generated method stub

		
		return notaPedidoRepository.save(notaPedido);
	}

	@Override
	public List<NotaPedido> getAll() {
		// TODO Auto-generated method stub
		return notaPedidoRepository.findAll();
	}

	public NotaPedido get(int Id){
		return  notaPedidoRepository.findById(Id);
	}

	@Override
	public NotaPedido asignarEspacios(NotaPedido notaPedido, Set<Espacio> espacios){
		notaPedido.setEspacios(espacios);
		notaPedido = notaPedidoRepository.save(notaPedido);
		return notaPedido;
	}

}
