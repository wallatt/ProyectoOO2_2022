package com.unla.grupo13.TrabajoPractico.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name ="nota_pedido")
public class NotaPedido extends EntityBase{

	@Id
	private int id;
	
	@Column(name="turno")
	private char turno;
	
	@Column (name="cantestudiantes")
	private int cantEstudiantes;
	
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
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

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

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
				+ examenFinal + " materia id: " +this.getMateria().getId() + "]";
	}
	
	
	
	
	
	
}
