package com.unla.grupo13.TrabajoPractico.entities;


import javax.persistence.Column;
import javax.persistence.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
@Entity
public class Departamento extends EntityBase{

	@Column(name="nombre")
	private String departamento;
	
	

}
