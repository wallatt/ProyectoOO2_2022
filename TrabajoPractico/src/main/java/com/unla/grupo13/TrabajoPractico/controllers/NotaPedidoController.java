package com.unla.grupo13.TrabajoPractico.controllers;

import java.sql.SQLOutput;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.unla.grupo13.TrabajoPractico.entities.*;
import com.unla.grupo13.TrabajoPractico.models.TradicionalModel;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.NotaPedidoModel;
import com.unla.grupo13.TrabajoPractico.services.IMateriaService;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;


@Controller
@RequestMapping("/")
public class NotaPedidoController {

	
	@Autowired
	@Qualifier("notaPedidoService")
	private INotaPedidoService notaPedidoService;
	
	@Autowired
	@Qualifier("materiaService")
	private IMateriaService materiaService;

	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;




	// todos los pedidos
	@GetMapping("pedidos")
	public ModelAndView getPedidos() {
		List<NotaPedido> lstNotaPedido = notaPedidoService.getAll();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDOS_ROOT);
		mAV.addObject("pedidos", lstNotaPedido);
		mAV.addObject("pedido", new NotaPedidoModel());

		return mAV;
	}
	
	@GetMapping("/pedidos/nuevo")
	public ModelAndView crearPedido() {
		
		//ModelAndView mAV=new ModelAndView(ViewRouteHelper.NUEVO_PEDIDO);
		List<Materia>listMaterias=materiaService.getAll();
		ModelAndView mAV=new ModelAndView(ViewRouteHelper.NUEVO_PEDIDO);
		mAV.addObject("notaPedido", new NotaPedido());
		mAV.addObject("listMaterias", listMaterias);
		
		return mAV;
	}

	
	@PostMapping("/crear")
	public RedirectView nuevoPedido(NotaPedido notaPedido) {
		
			
			notaPedidoService.save(notaPedido);
		
			return new RedirectView(ViewRouteHelper.PEDIDOS_OK);
	}
	
	@GetMapping ("/pedidos/okPedido")
	public ModelAndView verPedidos() {
		
		ModelAndView mAV=new ModelAndView(ViewRouteHelper.PEDIDOS_OK);
		return mAV;
	}
	@GetMapping ("/pedidos/{id_pedido}")
	public ModelAndView asignarPedidos(@PathVariable("id_pedido") int id_pedido){
		ModelAndView mAV=new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}
	@GetMapping ("/pedidos/{id_pedido}/aulasvalidas")
	public ModelAndView aulasValidas(@PathVariable("id_pedido")int id_pedido) {
		ModelMapper mapper = new ModelMapper();
		ModelAndView mAV=new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS_AULAS);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Espacio> espacios = espacioService.getByTurno(notaPedido.getTurno());
		Set<Tradicional> trads= new HashSet<Tradicional>();
		Set<Laboratorio> labs= new HashSet<Laboratorio>();

		Set<TradicionalModel> tradicionalModels = new HashSet<TradicionalModel>();
		String clase;

		//Obtengo las diferentes aulas de los espacios
		//separados en laboratorio y tradicional
		for (Espacio e:espacios ) {
//			clase = e.getAula().toString();
//			if(clase.charAt(0)==('L')){
			if(Hibernate.unproxy(e.getAula()) instanceof Laboratorio){
				labs.add((Laboratorio) Hibernate.unproxy(e.getAula()));
			}else{
				trads.add((Tradicional) Hibernate.unproxy(e.getAula()));
			}
		}
		for(Tradicional trad : trads){
			TradicionalModel tradicionalModel = mapper.map(trad, TradicionalModel.class);
			tradicionalModel.setEspacios(espacioService.traerEspaciosDeAula(trad, notaPedido.getTurno()));
			tradicionalModels.add(tradicionalModel);
		}



		//traer espacios

		//obtener diferentes aulas


		//List<Espacio> espacios = espacioService.
		mAV.addObject("pedido", notaPedido);
		mAV.addObject("espacios",espacios);
		mAV.addObject("labs",labs);
		mAV.addObject("trads",tradicionalModels);


		return mAV;
	}









}
