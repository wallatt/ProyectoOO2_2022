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

	
	public NotaPedido() {}
	
	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public int getCantEstudiantes() {
		return cantEstudiantes;
	}

	public void setCantEstudiantes(int cantEstudiantes) {
		this.cantEstudiantes = cantEstudiantes;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getProfesores() {
		return profesores;
	}

	public void setProfesores(String profesores) {
		this.profesores = profesores;
	}

	public boolean isExamenFinal() {
		return examenFinal;
	}

	public void setExamenFinal(boolean examenFinal) {
		this.examenFinal = examenFinal;
	}

	public Set<Espacio> getEspacios() {
		return espacios;
	}

	public void setEspacios(Set<Espacio> espacios) {
		this.espacios = espacios;
	}

	@Override
	public String toString() {
		return "NotaPedido [turno=" + turno + ", cantEstudiantes=" + cantEstudiantes + ", observaciones="
				+ observaciones + ", codCurso=" + codCurso + ", profesores=" + profesores + ", examenFinal="
				+ examenFinal + "]";
	}
	
	
	
	
	
	
}
