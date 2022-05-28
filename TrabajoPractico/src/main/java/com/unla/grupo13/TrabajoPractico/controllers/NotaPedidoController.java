package com.unla.grupo13.TrabajoPractico.controllers;

import java.util.List;

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

import com.unla.grupo13.TrabajoPractico.entities.Materia;
import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
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
	
	
}
