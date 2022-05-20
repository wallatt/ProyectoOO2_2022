package com.unla.grupo13.TrabajoPractico.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HelloWorldController {

    @GetMapping("/helloworld")
    public String helloWorld(){
        return "index";
    }
}
