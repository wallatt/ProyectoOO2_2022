package com.unla.grupo13.TrabajoPractico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo13.TrabajoPractico.entities.Edificio;
import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.EdificioModel;
import com.unla.grupo13.TrabajoPractico.services.IEdificioService;
@Controller
@RequestMapping("/edificios")
public class EdificioController {

	
	@Autowired
	@Qualifier("edificioService")
	private IEdificioService edificioService;
	
	
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EDIFICIO_INDEX);
		mAV.addObject("edificios", edificioService.getAll());
		mAV.addObject("edificio", new EdificioModel());
		
		return mAV;
		
		
	}
	
	
}
