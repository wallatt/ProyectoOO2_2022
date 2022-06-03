package com.unla.grupo13.TrabajoPractico.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class NotaPedidoModel {
	private int id;
	
	private LocalDate fecha;
	
	private String codCurso;
	
	private String profesores;
	
	private char turno;
	
	private int idMateria;
	
	private String observaciones;

	private List<EspacioModel> espacioModelList;

	public List<EspacioModel> getEspacioModelList() {
		return espacioModelList;
	}
	public void setEspacioModelList(List<EspacioModel> espacioModelList) {
		this.espacioModelList = espacioModelList;
	}


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
	
	public NotaPedidoModel() {};
	
}
