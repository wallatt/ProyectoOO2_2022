package com.unla.grupo13.TrabajoPractico.services;

import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Tradicional;
import org.springframework.data.jpa.repository.Query;

public interface IAulaService {

	
	public List<Aula> getAll();
	public List<Laboratorio> getByEdificio(int id);
	public List<Tradicional> getByEdificio2(int id);

	public Aula getById(int id);
	public List <Laboratorio> findEspaciosLab(char turno,boolean libre,int cantEstudiantes,int cantPc);
	public List<Tradicional> findEspaciosTrad(char turno,boolean libre,int cantEstudiantes,boolean proyector, int disponibilidad);

	public void uploadAllTradicional(boolean libre, char turno, int id, int id_pedido, int disponibilidad, int diaSemana);
}
