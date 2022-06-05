package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.models.NotaPedidoModel;
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
	
	private ModelMapper modelMapper=new ModelMapper();

	@Override
	public List<NotaPedido> getAll() {
		// TODO Auto-generated method stub
		return notaPedidoRepository.findAll();
	}

	@Override
	public NotaPedido findById(int id) {
		return notaPedidoRepository.findById(id);
	}

	@Override
	public NotaPedidoModel save(NotaPedido notaPedido) {
		
		NotaPedido nuevoNP = notaPedidoRepository.save(notaPedido);
		return modelMapper.map(nuevoNP, NotaPedidoModel.class);
	}


}
