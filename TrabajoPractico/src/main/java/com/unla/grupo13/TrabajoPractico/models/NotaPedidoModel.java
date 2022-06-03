package com.unla.grupo13.TrabajoPractico.models;

import java.time.LocalDate;

import com.unla.grupo13.TrabajoPractico.entities.Materia;

public class NotaPedidoModel {
	private int id;
	
	private LocalDate fecha;
	
	private String codCurso;
	
	private String profesores;
	
	private char turno;
	
	private int idMateria;
	
	private Materia materia;
	
	private String observaciones;
	
	private int cantEstudiantes;
	
	private boolean examenFinal;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
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

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	
	public int getCantEstudiantes() {
		return cantEstudiantes;
	}

	public void setCantEstudiantes(int cantEstudiantes) {
		this.cantEstudiantes = cantEstudiantes;
	}

	public boolean isExamenFinal() {
		return examenFinal;
	}

	public void setExamenFinal(boolean examenFinal) {
		this.examenFinal = examenFinal;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public NotaPedidoModel() {};
	
}
