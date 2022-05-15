package com.unla.grupo13.TrabajoPractico.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo13.TrabajoPractico.entities.User;
import com.unla.grupo13.TrabajoPractico.entities.UserRole;
import com.unla.grupo13.TrabajoPractico.helpers.ViewRouteHelper;
import com.unla.grupo13.TrabajoPractico.models.UserModel;
import com.unla.grupo13.TrabajoPractico.services.IRolService;
import com.unla.grupo13.TrabajoPractico.services.IUserService;



	@Controller
	@RequestMapping("/user")
	public class UserController {
		
		@Autowired
		@Qualifier("userService")
		private IUserService userService;
		
		@Autowired
		@Qualifier("rolService")
		private IRolService rolService;
		
		private ModelMapper modelMapper= new ModelMapper();
		
		
		@GetMapping("")
		public ModelAndView index() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_INDEX);
			return mAV;
		}
		
		@GetMapping("/registro")
		public ModelAndView create() {
			ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
			mAV.addObject("user", new UserModel());
			return mAV;
		}
		
		@GetMapping("/exito")
		public ModelAndView exito() {
			
			ModelAndView mAV= new ModelAndView (ViewRouteHelper.EXITO);
			return mAV;
		}
		
		@PostMapping("/create")
		public RedirectView create(@ModelAttribute("user") UserModel userModel) {
			UserRole role=rolService.findById(1);
			userModel.setRole(role);
			userModel.setEnabled(true);
			userService.save(modelMapper.map(userModel, User.class));
			return new RedirectView(ViewRouteHelper.USER_ROOT);
		}
	
		@GetMapping("/asistente")
		public ModelAndView asistente() {
			
			ModelAndView mAV= new ModelAndView (ViewRouteHelper.USER_ASISTENTE);
			mAV.addObject("user", new UserModel());
			return mAV;
		}
		
		
		
		@PostMapping ("/asistente/create")
		public RedirectView createAsistente(@ModelAttribute ("user")UserModel userModel) {
			
			UserRole role=rolService.findByRole("USER_ASISTENTE");
			userModel.setRole(role);
			userModel.setEnabled(true);
			userService.save(modelMapper.map(userModel, User.class));
			return new RedirectView(ViewRouteHelper.EXITO_ASISTENTE);
		}
			@GetMapping ("/asistente/exitoasistente")
			public ModelAndView exitoAsistente(){
			ModelAndView mAV= new ModelAndView (ViewRouteHelper.EXITO_USER_ASISTENTE);
			return mAV;
			}
		
			@GetMapping("/byusername")
			public ModelAndView getByUserName(@ModelAttribute ("userName")String userName ){
				ModelAndView mAV = new ModelAndView(ViewRouteHelper.DATOS_USER);
				mAV.addObject("user", userService.findByUserName(userName));
				return mAV;
			}
		
		
			@GetMapping("/perfil")
			public ModelAndView perfil() {
				ModelAndView modelAndView = new ModelAndView(ViewRouteHelper.PERFIL);
				User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				modelAndView.addObject("username", user.getUserName());
				return modelAndView;
			}
		
		
}
