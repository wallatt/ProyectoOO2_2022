package com.unla.grupo13.TrabajoPractico.services.imp;

import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

import com.unla.grupo13.TrabajoPractico.entities.*;
import com.unla.grupo13.TrabajoPractico.repositories.IEspacioRepository;
import com.unla.grupo13.TrabajoPractico.repositories.INotaPedidoRepository;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.unla.grupo13.TrabajoPractico.repositories.IAulaRepository;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;

import javax.swing.plaf.synth.SynthTextAreaUI;


@Service("aulaService")
public class AulaService implements IAulaService{

	@Autowired
	@Qualifier("aulaRepository")
	private IAulaRepository aulaRepository;

	@Autowired
	@Qualifier("notaPedidoRepository")
	private INotaPedidoRepository notaPedidoRepository;

	@Autowired
	@Qualifier("espacioRepository")
	private IEspacioRepository espacioRepository;

	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;

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
	public List <Laboratorio> findEspaciosLab(char turno, boolean libre, int cantEstudiantes, int cantPc, int disponibilidad, int diaSemana) {
		// TODO Auto-generated method stub
		List<Laboratorio> list = new ArrayList<Laboratorio>();

		Set<Espacio> porDias = new HashSet<Espacio>();

		//porDias = espacioService.traerEspacioDia(diaSemana, )

		int cantLibres = 0;

		for(Laboratorio lab : aulaRepository.findEspaciosLab(turno, libre, cantEstudiantes, cantPc)){
			lab.setEspacios(espacioRepository.findByAulaIdAndTurno(lab.getId(), turno));

			porDias = espacioService.traerEspacioDia(diaSemana, lab.getEspacios());
			for(Espacio e : lab.getEspacios()){

				if(e.isLibre() && e.getFecha().getDayOfWeek().getValue() == diaSemana){

					cantLibres ++;
				}
			}

			System.out.println("\nCANT LIBRE\n" + cantLibres);
			System.out.println("\nESPACIOS SIZE\n" + porDias.size());
			System.out.println("\nDISPONIBILIDAD\n" + disponibilidad);

			if((cantLibres / porDias.size()) >= disponibilidad){
				list.add(lab);
			}

			cantLibres = 0;
		}

		return list;
	}

	@Override
	public List <Tradicional> findEspaciosTrad(char turno, boolean libre, int cantEstudiantes, boolean proyector, int disponibilidad, int diaSemana) {
		// TODO Auto-generated method stub

		int cantLibres = 0;

		List<Tradicional> list = new ArrayList<Tradicional>();

		for(Tradicional trad : aulaRepository.findEspaciosTrad(turno, libre, cantEstudiantes, proyector)){
			for(Espacio e : trad.getEspacios()){
				if(e.isLibre() && e.getFecha().getDayOfWeek().getValue() == diaSemana){
					cantLibres ++;
				}
			}

			if((cantLibres / trad.getEspacios().size()) >= disponibilidad){
				list.add(trad);
			}

			cantLibres = 0;
		}

		return list;
	}

	public void asignarTrad(char turno,boolean libre, int porcetanjeDisponibilidad, int id, int id_pedido, int diaSemana){
		Tradicional trad = aulaRepository.findByIdTradWithEspacios(turno, true, id);

		NotaPedido pedido = notaPedidoRepository.findById(id_pedido);

		int weekNumber = 0;

		int cantLibres = 0;

		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

		Set<Espacio> list = new HashSet<Espacio>();

		list = espacioService.traerEspacioDia(diaSemana, trad.getEspacios());

		System.out.println("\nPORCENTAJE DE FRONT \n" + porcetanjeDisponibilidad);

		System.out.println("\nTAMAÑO LISTA\n" + list.size());

		for(Espacio e : trad.getEspacios()){
			if(e.isLibre() && e.getFecha().getDayOfWeek().getValue() == diaSemana){
				cantLibres ++;
			}
		}

		if(cantLibres == porcetanjeDisponibilidad){

			System.out.println("\nLLEGA HASTA EL IF?\n");

			for (Espacio e : list){
				e.setLibre(false);
				e.setNotaPedido(pedido);
				espacioRepository.save(e);
			}
		}
		else{
			for (Espacio e : list){
				weekNumber = e.getFecha().get(woy);
				if(weekNumber%2 != 0){
					e.setLibre(false);
					e.setNotaPedido(pedido);
					espacioRepository.save(e);
				}
			}
		}

		pedido.setSoftDelete(false);

		System.out.println("\ndisponibilidad:\n" + porcetanjeDisponibilidad);
		System.out.println("\nturno:\n" + turno);
		System.out.println("\nid_pedido:\n" + id_pedido);
		System.out.println("\nid_aula:\n" + id);

		trad.setPorcetanjeDisponibilidad(trad.getPorcetanjeDisponibilidad() - porcetanjeDisponibilidad);

		aulaRepository.save(trad);

		notaPedidoRepository.save(pedido);
	}

	public void asignarLab(char turno, boolean libre, int porcetanjeDisponibilidad, int id, int id_pedido, int diaSemana){
		Laboratorio lab = aulaRepository.findByIdLabWithEspacios(turno, true, id);

		NotaPedido pedido = notaPedidoRepository.findById(id_pedido);

		int weekNumber = 0;

		TemporalField woy = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();

		Set<Espacio> list = new HashSet<Espacio>();

		list = espacioService.traerEspacioDia(diaSemana, lab.getEspacios());

		if(lab.getPorcetanjeDisponibilidad() == porcetanjeDisponibilidad){
			for (Espacio e : list){
				e.setLibre(false);
				e.setNotaPedido(pedido);
				espacioRepository.save(e);
			}
		}
		else{
			for (Espacio e : list){
				weekNumber = e.getFecha().get(woy);
				if(weekNumber%2 != 0){
					e.setLibre(false);
					e.setNotaPedido(pedido);
					espacioRepository.save(e);
				}
			}
		}

		pedido.setSoftDelete(false);

		lab.setPorcetanjeDisponibilidad(lab.getPorcetanjeDisponibilidad() - porcetanjeDisponibilidad);

		aulaRepository.save(lab);

		notaPedidoRepository.save(pedido);

	}

}
