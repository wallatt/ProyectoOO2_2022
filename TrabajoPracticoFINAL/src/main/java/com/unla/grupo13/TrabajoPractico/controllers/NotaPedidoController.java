package com.unla.grupo13.TrabajoPractico.controllers;

import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.time.LocalDateTime;
import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.stream.Collectors;

import com.unla.grupo13.TrabajoPractico.models.*;
import org.modelmapper.ModelMapper;
import com.unla.grupo13.TrabajoPractico.entities.*;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.services.IMateriaService;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;

@Controller
@RequestMapping("/")
@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ASISTENTE')")
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

	private ModelMapper modelMapper = new ModelMapper();

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

		// ModelAndView mAV=new ModelAndView(ViewRouteHelper.NUEVO_PEDIDO);
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

	@PostMapping("/pedidos/editar/save/{id}")
	public String editarPedido(@PathVariable("id") int id,
			@ModelAttribute("notaPedido") NotaPedidoModel notaPedidoModel) {

		NotaPedido notaPedidoUpdate = notaPedidoService.findById(id);
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

		mAV.addObject("pedido", notaPedidoService.findById(id));
		mAV.addObject("listMaterias", listMaterias);

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pedidos/{id_pedido}")
	public ModelAndView asignarPedidos(@PathVariable("id_pedido") int id_pedido) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS);
		NotaPedido notaPedido = notaPedidoService.get(id_pedido);
		mAV.addObject("pedido", notaPedido);
		mAV.addObject("parametros", new Parametros());

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Set<Espacio> filtrarEspacios(Parametros parametros, List<Espacio> espacios) {

		Comparator<Espacio> compararPorfecha = Comparator.comparing(Espacio::getFecha);
		espacios = espacios.stream().sorted(compararPorfecha).collect(Collectors.toList());

		int cantidadEspacios = espacios.size();

		Set<Espacio> filtrados = new HashSet<>();
		if (parametros.getTipopresencial() == 0) {
			for (Espacio e : espacios) {
				// aniado solo espacios del dia de la notapedido
				if (e.isLibre()) {
					filtrados.add(e);
				}
			}
		} else {
			LocalDate fechaAComparar = espacios.get(0).getFecha();
			if (parametros.getTipopresencial() == 1) {
				for (Espacio e : espacios) {
					int dias = (int) Duration.between(fechaAComparar.atStartOfDay(), e.getFecha().atStartOfDay())
							.toDays();
					if (dias % 14 == 0) {
						filtrados.add(e);
					}
				}

			} else {
				// Si es final remuevo espacios que no son fecha de final
				for (Espacio e : espacios) {
					// remuevo espacios que no son de la fecha
					if (e.getFecha().equals(parametros.getFechaFinal())) {
						filtrados.add(e);
					}
				}
			}
		}
		return filtrados;
	}

	// traigo los espacios sin mayor considerancion en filtros
	// y se filtran en este metodo los que cumplan las condiciones
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/pedidos/{id_pedido}/aulasvalidadas")
	public ModelAndView aulasValidadas(@PathVariable("id_pedido") int id_pedido,
			@ModelAttribute("parametros") Parametros parametros) {

		ModelMapper mapper = new ModelMapper();
		ModelAndView mAV;

		if (parametros.esLaboratorio()) {
			mAV = new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS_AULAS_LABORATORIO);
		} else {
			mAV = new ModelAndView(ViewRouteHelper.GESTION_PEDIDOS_AULAS_TRADICIONAL);
		}

		NotaPedido notaPedido = notaPedidoService.get(id_pedido);

		FiltroEspacios filtro = new FiltroEspacios();

		int diaSemana = parametros.getDiaSemana();
		//trae todos los espacios para un turno
		if(parametros.getTipopresencial()==2){
			DayOfWeek dayOfWeek = parametros.getFechaFinal().getDayOfWeek();
			diaSemana = dayOfWeek.getValue(); // 6
		}
		List<Espacio> espaciosDeTurno = espacioService.getByTurnoAndDiaSemana(parametros.getTurnoMateria(), diaSemana);
		
		
		// trae todos los espacios para un turno

		Set<Aula> aulasYEspaciosFiltrados = filtro.filtrarEspacios(parametros, espaciosDeTurno);

		// Set<Espacio> espacios = filtrarEspacios(parametros, espaciosDeTurno);

		Set<Tradicional> trads = new HashSet<Tradicional>();
		Set<Laboratorio> labs = new HashSet<Laboratorio>();

		// filtrado por cant alumnos
		for (Aula a : aulasYEspaciosFiltrados) {
			if (Hibernate.unproxy(a) instanceof Laboratorio) {
				Laboratorio lab = (Laboratorio) Hibernate.unproxy(a);
				if (lab.getCantPc() >= parametros.getNumAsientos()
						&& lab.getCantSillas() >= parametros.getNumAsientos()) {
					labs.add(lab);
				}
			} else {
				Tradicional trad = (Tradicional) Hibernate.unproxy(a);
				if (trad.getCantBancos() >= parametros.getNumAsientos()) {
					trads.add(trad);
				}
			}
		}

//		//Obtengo las diferentes aulas de los espacios
//		//separados en laboratorio y tradicional
//		for (Espacio e:espacios ) {
//			//remuevo aulas que no tienen suficientes lugares
//			if(Hibernate.unproxy(e.getAula()) instanceof Laboratorio){
//				Laboratorio lab = (Laboratorio) Hibernate.unproxy(e.getAula());
//				if(lab.getCantPc()>= parametros.getNumAsientos() && lab.getCantSillas() >= parametros.getNumAsientos()) {
//					labs.add(lab);
//				}
//			}else{
//				Tradicional trad = (Tradicional) Hibernate.unproxy(e.getAula());
//				if(trad.getCantBancos()>= parametros.getNumAsientos()) {
//
//					trads.add(trad);
//				}

		List<TradicionalModel> tradicionalModels = new LinkedList<TradicionalModel>();
		List<LaboratorioModel> laboratorioModels = new LinkedList<LaboratorioModel>();

		// Ordeno aulas por ID
		if (parametros.esLaboratorio()) {
			for (Laboratorio lab : labs) {
				LaboratorioModel laboratorioModel = mapper.map(lab, LaboratorioModel.class);
				// laboratorioModel.setEspacios(espacioService.traerEspaciosDeAula(lab,
				// parametros.getTurnoMateria(),true));
				laboratorioModels.add(laboratorioModel);

			}
			Comparator<LaboratorioModel> compararPorNumero = Comparator.comparing(LaboratorioModel::getId);
			laboratorioModels = laboratorioModels.stream().sorted(compararPorNumero).collect(Collectors.toList());

		} else {
			for (Tradicional trad : trads) {
				TradicionalModel tradicionalModel = mapper.map(trad, TradicionalModel.class);
				// tradicionalModel.setEspacios(espacioService.traerEspaciosDeAula(trad,
				// parametros.getTurnoMateria(),true));
				tradicionalModels.add(tradicionalModel);
			}
			Comparator<TradicionalModel> compararPorNumero = Comparator.comparing(TradicionalModel::getId);
			tradicionalModels = tradicionalModels.stream().sorted(compararPorNumero).collect(Collectors.toList());
		}

		if (parametros.esLaboratorio()) {
			mAV.addObject("labs", labs);
			mAV.addObject("labsmodel", laboratorioModels);
		} else {
			mAV.addObject("trads", trads);
			mAV.addObject("tradsmodel", tradicionalModels);
		}

		// List<Espacio> espacios = espacioService.
		mAV.addObject("pedido", notaPedido);
		// mAV.addObject("espacios", espacios);

		mAV.addObject("parametros", parametros);
		System.out.println(parametros.toString());

		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/pedidos/{id_pedido}/aulaasignada")
	public ModelAndView asignarEspacios(@PathVariable("id_pedido") int id_pedido,
			@ModelAttribute("parametros") Parametros parametros) {
		ModelAndView mV = new ModelAndView();
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.GESTION_ESPACIOS);

		NotaPedido notaPedido = notaPedidoService.get(id_pedido);

//		List<Espacio> espacios = espacioService.getByTurno(parametros.getTurnoMateria());
//
		List<Aula> aulasFiltrados = new ArrayList<Aula>();
		FiltroEspacios filtro = new FiltroEspacios();

		int diaSemana = parametros.getDiaSemana();
		//trae todos los espacios para un turno
		if(parametros.getTipopresencial()==2){
			DayOfWeek dayOfWeek = parametros.getFechaFinal().getDayOfWeek();
			diaSemana = dayOfWeek.getValue(); // 6
		}
		List<Espacio> espaciosDeTurno = espacioService.getByTurnoAndDiaSemana(parametros.getTurnoMateria(), diaSemana);

		Set<Aula> aulasYEspaciosFiltrados = filtro.filtrarEspacios(parametros, espaciosDeTurno);

		// aniado solo espacios del aula especifica
		System.out.println();
		for (Aula a : aulasYEspaciosFiltrados) {
			if (a.getId() == parametros.getAulaId()) {
				aulasFiltrados.add(a);
			}
		}
		System.out.println("largo de filtrados " + aulasFiltrados.size());

		// quedan filtrados por fecha por tipo de presencialidad
		// Set<Espacio> espaciosAguardar = filtrarEspacios(parametros, filtrados);

		// System.out.println("largo de espacios a guardar "+ espaciosAguardar.size());

		for (Espacio e : aulasFiltrados.get(0).getEspacios()) {
			e.setLibre(false);
			e.setNotaPedido(notaPedido);
			System.out.println(e.toString());
		}

		notaPedido.setEspacios(aulasFiltrados.get(0).getEspacios());
		notaPedido.setSoftDelete(false);
		notaPedidoService.save(notaPedido);

		return mAV;
	}

}
