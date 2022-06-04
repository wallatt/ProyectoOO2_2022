package com.unla.grupo13.TrabajoPractico.controllers;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

	private ModelMapper modelMapper = new ModelMapper();

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("")
	public String VerUsuarios(Model model, @Param("userName") String userName) {

		List<User> users = userService.getAll(userName);
		model.addAttribute("users", users);
		model.addAttribute("userName", userName);

		return "/user/listausuarios";

	}

	@GetMapping("/registro")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_NEW);
		mAV.addObject("user", new UserModel());
		return mAV;
	}

	@GetMapping("/exito")
	public ModelAndView exito() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EXITO);
		return mAV;
	}

	@PostMapping("/create")
	public String create(@ModelAttribute("user") UserModel userModel) throws Exception {

		UserRole role = rolService.findByRole("ROLE_AUDITOR");

		userModel.setRole(role);
		userService.save(modelMapper.map(userModel, User.class));

		return "/user/exito";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/asistente")
	public ModelAndView asistente() {

		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_ASISTENTE);
		mAV.addObject("user", new UserModel());
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/asistente/create")
	public RedirectView createAsistente(@ModelAttribute("user") UserModel userModel) {

		UserRole role = rolService.findByRole("ROLE_ASISTENTE");
		userModel.setRole(role);
		userService.save(modelMapper.map(userModel, User.class));
		return new RedirectView(ViewRouteHelper.EXITO_ASISTENTE);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/asistente/exitoasistente")
	public ModelAndView exitoAsistente() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.EXITO_USER_ASISTENTE);
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/byusername")
	public ModelAndView getByUserName(@ModelAttribute("userName") String userName) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.DATOS_USER);
		mAV.addObject("user", userService.findByUserName(userName));
		return mAV;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/baja/{id}")
	public String darBajar(@PathVariable("id") int id) {

	
		userService.darBaja(id);
		return "redirect:/user";
	}

	@PostMapping("/editar/save/{id}")
	public String editarUser(@PathVariable("id") int id, @ModelAttribute("user") UserModel userModel) {

		userService.editar(modelMapper.map(userModel, User.class), id);

		return "user/exitoeditar";

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") int id) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.USER_EDITAR);
		mAV.addObject("user", userService.findById(id));
		return mAV;
	}

}
