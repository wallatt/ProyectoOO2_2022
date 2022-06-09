package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
@Entity
public class Departamento extends EntityBase{

	@Column(name="nombre")
	private String departamento;
	
	

}
