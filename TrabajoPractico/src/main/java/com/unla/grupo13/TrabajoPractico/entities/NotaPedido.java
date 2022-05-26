package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Getter @Setter @NoArgsConstructor
public class NotaPedido extends EntityBase{
	
	@Column(name="turno")
	private char turno;
	
	@Column (name="cantestudiantes")
	private int cantEstudiantes;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id", nullable = false)
	private Materia materia;
	
	@Column(name="observaciones")
	private String observaciones;
	
	@Column (name="codcurso")
	private String codCurso;
	
	@Column (name="profesores")
	private String profesores;
	
	@Column(name="examenfinal")
	private boolean examenFinal;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="notaPedido")
	private Set<Espacio> espacios=new HashSet<Espacio>();
	
	public NotaPedido(boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,char turno,int cantEstudiantes,Materia materia,String observaciones,String codCurso,Aula aula,boolean examenFinal,String profesores) {
		super(softDelete, fechaCreacion, fechaModificacion);
		// TODO Auto-generated constructor stub
		
		this.turno=turno;
		this.cantEstudiantes=cantEstudiantes;
		this.materia=materia;
		this.observaciones= observaciones;
		this.codCurso=codCurso;
		this.examenFinal=examenFinal;
		this.profesores=profesores;
		
	}
	
	
	
	
}
