package com.unla.grupo13.TrabajoPractico.controllers;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.unla.grupo13.TrabajoPractico.entities.*;
import com.unla.grupo13.TrabajoPractico.models.*;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
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
	ModelMapper modelMapper = new ModelMapper();

	@GetMapping("/pedidos/nuevo")
	public ModelAndView crearPedido() {

		List<Materia> listMaterias = materiaService.getAll();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.NUEVO_PEDIDO);
		mAV.addObject("notaPedido", new NotaPedido());
		mAV.addObject("listMaterias", listMaterias);

		return mAV;
	}

	@PostMapping("/crear")
	public RedirectView nuevoPedido(NotaPedido notaPedido) {

		notaPedidoService.save(notaPedido);

		return new RedirectView(ViewRouteHelper.PEDIDOS_OK);
	}

	@GetMapping("/pedidos/okPedido")
	public ModelAndView verPedidos() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDOS_OK);
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

	@PostMapping("/pedidos/editar/save/{id}")
	public String editarPedido(@PathVariable("id") int id,
							   @ModelAttribute("notaPedido") NotaPedidoModel notaPedidoModel) {

		NotaPedido notaPedidoUpdate = notaPedidoService.get(id);

		NotaPedido notaPedido = modelMapper.map(notaPedidoModel, NotaPedido.class);

		notaPedidoUpdate.setId(notaPedido.getId());
		notaPedidoUpdate.setCantEstudiantes(notaPedido.getCantEstudiantes());
		notaPedidoUpdate.setCodCurso(notaPedido.getCodCurso());
		notaPedidoUpdate.setExamenFinal(notaPedido.isExamenFinal());
		notaPedidoUpdate.setFechaCreacion(notaPedidoUpdate.getFechaCreacion());
		notaPedidoUpdate.setFechaModificacion(LocalDateTime.now());
		notaPedidoUpdate.setObservaciones(notaPedido.getObservaciones());
		notaPedidoUpdate.setTurno(notaPedido.getTurno());
		notaPedidoUpdate.setProfesores(notaPedido.getProfesores());
		notaPedidoUpdate.setMateria(notaPedido.getMateria());

		notaPedidoService.save(modelMapper.map(notaPedidoUpdate, NotaPedido.class));

		return "pedidos/okPedido";
	}

	@GetMapping("/pedidos/editar/{id}")
	@PostAuthorize("(returnObject.getModel().get('pedido')).isSoftDelete() == true")
	public ModelAndView verEditarPedido(@PathVariable("id") int id) {

		List<Materia> listMaterias = materiaService.getAll();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDO_EDITAR);

		mAV.addObject("pedido", notaPedidoService.get(id));
		mAV.addObject("listMaterias", listMaterias);

		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}")
	public ModelAndView asignarPedidos(@PathVariable("id_pedido") int id_pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}

	// TRADICIONALES

	@GetMapping("/pedidos/{id_pedido}/tradicionales")
	public ModelAndView buscarTradicionales(@PathVariable("id_pedido") int id_pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.VER_TRADICIONALES);

		mAV.addObject("pedido", notaPedidoService.get(id_pedido));
		mAV.addObject("parametros2", new TradicionalUploadModel());

		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/tradicionales/validas")
	public ModelAndView asignarEspaciosTrad(@ModelAttribute("parametros2") TradicionalUploadModel parametros2,
											@PathVariable("id_pedido") int id_pedido) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_ESPACIOS);

		System.out.println("\ndisp: " + parametros2.getDisponibidad());

		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Tradicional> trads = aulaService.findEspaciosTrad(notaPedido.getTurno(), true,
				notaPedido.getCantEstudiantes(), parametros2.isTieneProyector(), parametros2.getDisponibidad(), parametros2.getDiaSemana());

		mAV.addObject("parametros2", parametros2);
		mAV.addObject("trads", trads);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/tradicionales/validas/{id_aula}/espacios/{diaSemana}/disponibidad/{disponibidad}")
	public ModelAndView verEspaciosTrad(@PathVariable("id_pedido") int id_pedido, @PathVariable("id_aula") int id_aula,
										@PathVariable("diaSemana") int diaSemana, @PathVariable("disponibidad") int disponibidad) {

		//ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIOS_VER);

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CONFIRMAR_TRAD);

		System.out.println("\nVer espacios trad\n");

		Tradicional trad = (Tradicional) aulaService.getById(id_aula);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		//List<Espacio> espacios = espacioService.traerEspaciosDeAula(trad, notaPedido.getTurno());
		//List<Espacio> espaciosValidos = espacioService.traerEspacioDia(diaSemana, espacios);

		TradicionalUploadModel model = new TradicionalUploadModel();
		model.setDisponibidad(disponibidad);
		model.setDiaSemana(diaSemana);

		mAV.addObject("parametros2", model);
		mAV.addObject("trad", trad);
		//mAV.addObject("espacios", espaciosValidos);
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
	public ModelAndView asignarEspaciosLab(@ModelAttribute("parametros") LaboratorioUploadModel parametros,
										   @PathVariable("id_pedido") int id_pedido) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_ESPACIOS);

		System.out.println("\ndisp: " + parametros.getDisponibidad());

		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		List<Laboratorio> labs = aulaService.findEspaciosLab(notaPedido.getTurno(), true,
				notaPedido.getCantEstudiantes(), parametros.getCantPc(), parametros.getDisponibidad(), parametros.getDiaSemana());

		mAV.addObject("parametros", parametros);
		mAV.addObject("labs", labs);
		mAV.addObject("pedido", notaPedido);
		return mAV;
	}

	@GetMapping("/pedidos/{id_pedido}/laboratorios/validas/{id_aula}/espacios/{diaSemana}/disponibidad/{disponibidad}")
	public ModelAndView verEspaciosLab(@PathVariable("id_pedido") int id_pedido, @PathVariable("id_aula") int id_aula,
									   @PathVariable("diaSemana") int diaSemana, @PathVariable("disponibidad") int disponibidad) {

		//ModelAndView mAV = new ModelAndView(ViewRouteHelper.ESPACIOS_VER_LAB);

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CONFIRMAR_LAB);

		System.out.println("\nDisponibilidad en get\n" + disponibidad);



		Laboratorio lab = (Laboratorio) aulaService.getById(id_aula);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		//List<Espacio> espacios = espacioService.traerEspaciosDeAula(lab, notaPedido.getTurno());
		//List<Espacio> espaciosValidos = espacioService.traerEspacioDia(diaSemana, espacios);

		LaboratorioUploadModel model = new LaboratorioUploadModel();

		model.setDisponibidad(disponibidad);
		model.setDiaSemana(diaSemana);

		mAV.addObject("lab", lab);
		mAV.addObject("parametros2", model);
		//mAV.addObject("espacios", espaciosValidos);
		mAV.addObject("pedido", notaPedido);

		return mAV;
	}

	@PostMapping("/pedidos/{id_pedido}/asignarTrad/{id_aula}/disponibilidad/{disponibidad}/turno/{turno}/diaSemana/{diaSemana}")
	public String asignarTrad(@ModelAttribute("parametros2") TradicionalUploadModel parametros2,
									   @PathVariable("id_pedido") int id_pedido, @PathVariable("id_aula") int id_aula,
									   @PathVariable("disponibidad") int disponibidad, @PathVariable("turno") char turno,
							           @PathVariable("diaSemana") int diaSemana) {

		//ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDOS_ASIGNADO);

		System.out.println("Asignar trad");
		//Espacio e = espacioService.getById(parametros2.getId());
		//e.setNotaPedido(p);
		//e.setLibre(false);
		//p.setSoftDelete(false);
		//notaPedidoService.save(p);
		//espacioService.save(e);
		aulaService.asignarTrad(turno, true, disponibidad, id_aula, id_pedido, diaSemana);
		//NotaPedido p = notaPedidoService.get(id_pedido);
		//mAV.addObject("parametros2", parametros2);
		//mAV.addObject("p", p);
		//mAV.addObject("e", e);

		return "/pedidos/pedidos";

	}

	@PostMapping("/pedidos/{id_pedido}/asignarLab/{id_aula}/disponibilidad/{disponibidad}/turno/{turno}/diasemana/{diaSemana}")
	public String asignarLab(@ModelAttribute("parametros2") LaboratorioUploadModel parametros2,
									@PathVariable("id_pedido") int id_pedido, @PathVariable("id_aula") int id_aula,
									@PathVariable("disponibidad") int disponibidad, @PathVariable("turno") char turno,
							 @PathVariable("diaSemana") int diaSemana) {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PEDIDOS_ASIGNADO);
		System.out.println("Asignar lad");
		//Espacio e = espacioService.getById(parametros2.getId());
		//e.setNotaPedido(p);
		//e.setLibre(false);
		//p.setSoftDelete(false);
		//notaPedidoService.save(p);
		//espacioService.save(e);
		aulaService.asignarLab(turno, true, disponibidad, id_aula, id_pedido, diaSemana);
		NotaPedido p = notaPedidoService.get(id_pedido);
		mAV.addObject("parametros2", parametros2);
		mAV.addObject("p", p);
		//mAV.addObject("e", e);

		return "/pedidos/pedidos";

	}

}
