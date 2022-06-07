package com.unla.grupo13.TrabajoPractico.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;
import com.unla.grupo13.TrabajoPractico.services.IUserService;

@Controller
@RequestMapping("/")
public class HomeController {

    
	@Autowired
	@Qualifier("userService")
	private IUserService userService;

	
	 @GetMapping(value={"/","home"})
	    public ModelAndView home(){
		 
		 	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
	    	ModelAndView mAV = new ModelAndView(ViewRouteHelper.HOME);
	    	mAV.addObject("username", auth.getName());
	    	
	    	return mAV;
	    }
	@GetMapping("login")
	public String login(Model model, @RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return ViewRouteHelper.USER_LOGIN;
	}

	@GetMapping("logout")
	public String logout(Model model) {
		return ViewRouteHelper.USER_LOGOUT;
	}

	@GetMapping("loginsucces")
	public String loginCheck() {

		return "home/home";
	}

	// GET Example: SERVER/index
	@GetMapping("index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.INDEX);
		return modelAndView;
	}
    
}
