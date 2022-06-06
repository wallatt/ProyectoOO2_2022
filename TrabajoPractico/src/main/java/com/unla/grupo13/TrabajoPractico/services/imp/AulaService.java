package com.unla.grupo13.TrabajoPractico.services.imp;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.*;
import com.unla.grupo13.TrabajoPractico.repositories.IEspacioRepository;
import com.unla.grupo13.TrabajoPractico.repositories.INotaPedidoRepository;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.repositories.IAulaRepository;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;


@Service("aulaService")
public class AulaService implements IAulaService{

	@Autowired
	@Qualifier("aulaRepository")
	private IAulaRepository aulaRepository;

	@Autowired
	@Qualifier("espacioRepository")
	private IEspacioRepository espacioRepository;

	@Autowired
	@Qualifier("notaPedidoRepository")
	private INotaPedidoRepository notaPedidoRepository;
	
	@Override
	public List<Aula> getAll() {
		// TODO Auto-generated method stub
		return aulaRepository.findAll();
	}

	@Override
	public List<Laboratorio> getByEdificio(int id) {
		// TODO Auto-generated method stub
		return aulaRepository.findByEdificio(id);
	}

	@Override
	public List<Tradicional> getByEdificio2(int id) {
		// TODO Auto-generated method stub
		return aulaRepository.findByEdificio2(id);
	}

	@Override
	public Aula getById(int id) {
		// TODO Auto-generated method stub
		return aulaRepository.findById(id);
	}

	@Override
	public List <Laboratorio> findEspaciosLab(char turno, boolean libre, int cantEstudiantes, int cantPc) {



		// TODO Auto-generated method stub
		return aulaRepository.findEspaciosLab(turno, libre, cantEstudiantes, cantPc);
	}

	@Override
	public List <Tradicional> findEspaciosTrad(char turno, boolean libre, int cantEstudiantes, boolean proyector, int disponibilidad) {
		// TODO Auto-generated method stub
		return aulaRepository.findEspaciosTrad(turno, libre, cantEstudiantes, proyector, disponibilidad);
	}

	@Override
	public void uploadAllTradicional(boolean libre, char turno, int id, int id_pedido, int disponibilidad, int diaSemana){

		Tradicional trad = aulaRepository.findTradicionalWithEspacios(true, turno, id);

		NotaPedido pedido = notaPedidoRepository.findById(id_pedido);

		for(Espacio e : trad.getEspacios()){
			if(e.getFecha().getDayOfWeek().getValue() == diaSemana){
				e.setNotaPedido(pedido);
				espacioRepository.save(e);
			}
		}

		trad.setPorcetanjeDisponibilidad(trad.getPorcetanjeDisponibilidad() - disponibilidad);

		aulaRepository.save(trad);

	}

}
