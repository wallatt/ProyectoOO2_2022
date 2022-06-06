package com.unla.grupo13.TrabajoPractico.controllers;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.LaboratorioModel;
import com.unla.grupo13.TrabajoPractico.models.TradicionalModel;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/")
public class AulaController {

	
	@Autowired
	@Qualifier("aulaService")
	private IAulaService aulaService;

	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;

	@GetMapping("laboratorios/{id}")
	public ModelAndView verLabs (@PathVariable("id") int id) {
		
		

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LABORATORIO_INDEX);
		mAV.addObject("laboratorios", aulaService.getByEdificio(id));
		mAV.addObject("laboratorio", new LaboratorioModel());
		return mAV;
	}
	
	
	@GetMapping("tradicional/{id}")
	public ModelAndView verTrad (@PathVariable("id") int id) {
		
		

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.TRADICIONAL_INDEX);
		mAV.addObject("tradicionales", aulaService.getByEdificio2(id));
		mAV.addObject("tradicional", new TradicionalModel());
		return mAV;
	}

	@GetMapping ("/aula/okAula")
	public ModelAndView verPedidos() {

		ModelAndView mAV=new ModelAndView(ViewRouteHelper.AULA_OK);
		return mAV;
	}

	@PostMapping("/tradicional/{id}/notapedido/{id_pedido}/diasemana/{diaSemana}/disponibilidad/{disponibidad}/turno/{turno}")
	public RedirectView guardarEspaciosTrad(@PathVariable("id_pedido") int id_pedido, @PathVariable("id") int id, @PathVariable("turno") char turno,
											@PathVariable("diaSemana") int diaSemana, @PathVariable("disponibilidad") int disponibilidad) {

		//ModelAndView mAV = new ModelAndView(ViewRouteHelper.AULA_OK);

		//List<Espacio> espacios = espacioService.traerEspaciosDeAula(aulaService.getById(id_aula),notaPedido.getTurno());
		//List<Espacio> espaciosValidos=espacioService.traerEspacioDia(diaSemana, espacios);

		System.out.println("\nLlega hasta aca?\n");

		aulaService.uploadAllTradicional(true, turno, id, id_pedido, disponibilidad, diaSemana);

		/*for(Espacio e: trad.getEspacios()){
			//e.setNotaPedido();
		}*/

		//mAV.addObject("espacios", espaciosValidos);
		//mAV.addObject("pedido", notaPedido);

		return new RedirectView(ViewRouteHelper.AULA_OK);
	}
	
}
