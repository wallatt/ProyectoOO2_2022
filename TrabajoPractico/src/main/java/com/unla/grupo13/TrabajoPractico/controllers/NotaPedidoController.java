package com.unla.grupo13.TrabajoPractico.controllers;

import java.time.LocalDateTime;
import java.lang.annotation.Repeatable;
import java.util.List;

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

import com.unla.grupo13.TrabajoPractico.entities.Materia;
import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;
import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.NotaPedidoModel;
import com.unla.grupo13.TrabajoPractico.models.UserModel;
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

}
