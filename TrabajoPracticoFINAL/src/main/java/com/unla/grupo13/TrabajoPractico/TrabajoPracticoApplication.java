package com.unla.grupo13.TrabajoPractico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrabajoPracticoApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(TrabajoPracticoApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
