package com.unla.grupo13.TrabajoPractico.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.unla.grupo13.TrabajoPractico.services.INotaPedidoService;

@Controller
@RequestMapping
public class HelloWorldController {

	
	

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "index";
    }
    
    
}
