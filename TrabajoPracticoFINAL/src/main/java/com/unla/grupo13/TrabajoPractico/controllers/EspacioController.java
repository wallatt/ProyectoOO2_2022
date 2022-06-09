package com.unla.grupo13.TrabajoPractico.controllers;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;
import com.unla.grupo13.TrabajoPractico.models.EspacioModel;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.EdificioModel;
import com.unla.grupo13.TrabajoPractico.services.IEdificioService;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/")
public class EspacioController {

	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;

	@Autowired
	@Qualifier("aulaService")
	private IAulaService aulaService;

	@Autowired
	@Qualifier("notaPedidoService")
	private INotaPedidoService notaPedidoService;

	@GetMapping("/espacios/nuevo")
	public ModelAndView crearEspacio() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIO_NUEVO);
		List<Aula> listAulas = aulaService.getAll();
	
		mAV.addObject("listAulas", listAulas);
		mAV.addObject("espacioModel", new EspacioModel());

		return mAV;

	}

	@PostMapping("/crearespacio")
	public RedirectView nuevoPedido(@ModelAttribute("espacioModel") EspacioModel espacioModel) throws Exception {

		espacioService.generarEspacioMes(espacioModel.getFechaInicio(), espacioModel.getFechaFinalizacion(),
				espacioModel.getTurno());
		// espacioService.generarEspacioMes(espacioModel.getFechaInicio(),
		// espacioModel.getFechaFinalizacion(), espacioModel.getTurno());

		return new RedirectView(ViewRouteHelper.ESPACIO_OK);
	}

	@GetMapping("/espacio/okEspacio")
	public ModelAndView verPedidos() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIO_OK);
		return mAV;
	}

	// Consulta clases
	@PreAuthorize("hasRole('ROLE_AUDITOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_ASISTENTE') ")
	@GetMapping("/clases")
	public String verMisClases(@Param("codigoCurso") String codigoCurso, Model model) {

		List<Espacio> lstEspacio = espacioService.findByCodCurso(codigoCurso);
//		List<Espacio> allEspacio = espacioService.findAllAsignados();
		List<NotaPedido> lstPedido = notaPedidoService.getAll();

		model.addAttribute("codigoCurso", codigoCurso);
		model.addAttribute("espacios", lstEspacio);
//		model.addAttribute("todosEspacios", allEspacio);
		model.addAttribute("codigos", lstPedido);

		return "/pedidos/clases";
	}
}