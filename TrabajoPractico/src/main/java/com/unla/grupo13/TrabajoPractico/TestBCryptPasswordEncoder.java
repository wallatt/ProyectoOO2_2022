package com.unla.grupo13.TrabajoPractico;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder pe = new BCryptPasswordEncoder(4);
		System.out.println(pe.encode("123"));
	}

}
