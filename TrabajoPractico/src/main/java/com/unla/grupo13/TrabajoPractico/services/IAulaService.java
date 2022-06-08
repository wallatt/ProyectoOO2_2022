package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;

public interface IAulaService {


	public List<Aula> getAll();
	public List<Laboratorio> getByEdificio(int id);
	public List<Tradicional> getByEdificio2(int id);
	public List<Tradicional> findEspaciosTrad(char turno, boolean b, int cantEstudiantes, boolean tieneProyector, int disponibilidad, int diaSemana);
	public Aula getById(int id_aula);
	public List<Laboratorio> findEspaciosLab(char turno, boolean libre, int cantEstudiantes, int cantPc, int disponibilidad, int diaSemana);

	public void asignarTrad(char turno,boolean libre, int porcetanjeDisponibilidad, int id, int id_pedido, int diaSemana);

	public void asignarLab(char turno, boolean libre, int porcetanjeDisponibilidad, int id, int id_pedido, int diaSemana);
}
