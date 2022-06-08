package com.unla.grupo13.TrabajoPractico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.LaboratorioModel;
import com.unla.grupo13.TrabajoPractico.models.TradicionalModel;
import com.unla.grupo13.TrabajoPractico.services.IAulaService;





@Controller
@RequestMapping("/")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AulaController {


	@Autowired
	@Qualifier("aulaService")
	private IAulaService aulaService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("laboratorios/{id}")
	public ModelAndView verLabs (@PathVariable("id") int id) {



		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LABORATORIO_INDEX);
		mAV.addObject("laboratorios", aulaService.getByEdificio(id));
		mAV.addObject("laboratorio", new LaboratorioModel());
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("tradicional/{id}")
	public ModelAndView verTrad (@PathVariable("id") int id) {



		ModelAndView mAV = new ModelAndView(ViewRouteHelper.TRADICIONAL_INDEX);
		mAV.addObject("tradicionales", aulaService.getByEdificio2(id));
		mAV.addObject("tradicional", new TradicionalModel());
		return mAV;
	}

}
