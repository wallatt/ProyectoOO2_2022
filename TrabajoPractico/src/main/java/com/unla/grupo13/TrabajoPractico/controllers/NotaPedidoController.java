package com.unla.grupo13.TrabajoPractico.controllers;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.*;
import com.unla.grupo13.TrabajoPractico.models.LaboratorioUploadModel;
import com.unla.grupo13.TrabajoPractico.models.TradicionalUploadModel;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
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
	@Qualifier("aulaService")
	private IAulaService aulaService;

	@Autowired
	@Qualifier("materiaService")
	private IMateriaService materiaService;

	@Autowired
	@Qualifier("espacioService")
	private IEspacioService espacioService;
	
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

	// todos los pedidos
	@GetMapping("pedidos")
	public ModelAndView getPedidos() {
		List<NotaPedido> lstNotaPedido = notaPedidoService.getAll();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDOS_ROOT);
		mAV.addObject("pedidos", lstNotaPedido);
		mAV.addObject("pedido", new NotaPedidoModel());

		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}")
	public ModelAndView asignarPedidos(@PathVariable("id_pedido") int id_pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}

	//TRADICIONALES

	@GetMapping("/pedidos/{id_pedido}/tradicionales")
	public ModelAndView buscarTradicionales(@PathVariable("id_pedido") int id_pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VER_TRADICIONALES);

		mAV.addObject("pedido", notaPedidoService.get(id_pedido));
		mAV.addObject("parametros2", new TradicionalUploadModel());

		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/tradicionales/validas")
	public ModelAndView asignarEspaciosTrad(@ModelAttribute("parametros2") TradicionalUploadModel upload,
											@PathVariable("id_pedido") int id_pedido) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_ESPACIOS);

		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Tradicional> trads = aulaService.findEspaciosTrad(notaPedido.getTurno(), true,
				notaPedido.getCantEstudiantes(), upload.isTieneProyector(), upload.getDisponibidad());

		System.out.println("\ndisponibilidad" + upload.getDisponibidad());

		mAV.addObject("parametros2",upload);
		mAV.addObject("trads", trads);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/tradicionales/validas/{id_aula}/espacios/{diaSemana}")
	public ModelAndView verEspaciosTrad(@PathVariable("id_pedido") int id_pedido, @PathVariable("id_aula") int id_aula,@PathVariable("diaSemana") int diaSemana) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIOS_VALIDOS);



		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Espacio> espacios = espacioService.traerEspaciosDeAula(aulaService.getById(id_aula),notaPedido.getTurno());
		List<Espacio> espaciosValidos=espacioService.traerEspacioDia(diaSemana, espacios);

		mAV.addObject("espacios", espaciosValidos);
		mAV.addObject("pedido", notaPedido);

		return mAV;
	}

	// LABORATORIOS

	@GetMapping("/pedidos/{id_pedido}/laboratorios")
	public ModelAndView buscarLaboratorios(@PathVariable("id_pedido") int id_pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VER_LABS);

		mAV.addObject("pedido", notaPedidoService.get(id_pedido));
		mAV.addObject("parametros", new LaboratorioUploadModel());

		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/laboratorios/validas")
	public ModelAndView asignarEspaciosLab(@ModelAttribute("parametros") LaboratorioUploadModel upload,
										   @PathVariable("id_pedido") int id_pedido) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_ESPACIOS);

		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Laboratorio> labs =  aulaService.findEspaciosLab(notaPedido.getTurno(), true,
				notaPedido.getCantEstudiantes(), upload.getCantPc());

		mAV.addObject("parametros",upload);
		mAV.addObject("labs", labs);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/laboratorios/validas/{id_aula}/espacios/{diaSemana}")
	public ModelAndView verEspaciosLab(@PathVariable("id_pedido") int id_pedido, @PathVariable("id_aula") int id_aula,@PathVariable("diaSemana") int diaSemana) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIOS_VALIDOS);



		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Espacio> espacios = espacioService.traerEspaciosDeAula(aulaService.getById(id_aula),notaPedido.getTurno());
		List<Espacio> espaciosValidos=espacioService.traerEspacioDia(diaSemana, espacios);

		mAV.addObject("espacios", espaciosValidos);
		mAV.addObject("pedido", notaPedido);

		return mAV;
	}


}
