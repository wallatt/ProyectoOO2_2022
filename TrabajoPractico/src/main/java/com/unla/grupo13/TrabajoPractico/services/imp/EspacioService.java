package com.unla.grupo13.TrabajoPractico.services.imp;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.repositories.IEspacioRepository;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("espacioService")
public class EspacioService implements IEspacioService {

    @Autowired
    @Qualifier("espacioRepository")
    private IEspacioRepository espacioRepository;

    @Override
    public Espacio generarEspacios(Aula aula, char turno, LocalDate fecha, boolean libre) throws Exception {
        // TODO Auto-generated method stub

        Espacio e = espacioRepository.findByLibreFechaAula(aula, turno, fecha);

        if (e != null) {

            throw new Exception("espacio ya registrado");

        }

        e = new Espacio(fecha, turno, aula, libre);

        return espacioRepository.save(e);
    }

    public void generarEspacioMes(String fechaInicio, String fechaFinalizacion, char turno, Aula aula) throws Exception {

        //int diasDelMes = funciones.diasDelMes(anio, mes);

        LocalDate inicio = LocalDate.parse(fechaInicio);

        LocalDate fin = LocalDate.parse(fechaFinalizacion);

        LocalDate aux = inicio;

        while(aux.isBefore(fin.plusDays(1))){
            if(aux.getDayOfWeek().getValue() != 6 && aux.getDayOfWeek().getValue() != 7){
                this.generarEspacios(aula, turno, aux, true);
            }


            aux = aux.plusDays(1);
        }

    }

    public List<Espacio> getByTurno(char turno){
        List<Espacio> espacios = espacioRepository.findByTurno(turno);
        return espacios;
    }


	@Override
	public List<Espacio> getByAulaId(int id_aula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Espacio> getByTurno1(char turno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Espacio> traerEspaciosDeAula(Aula aula, char turno) {
		// TODO Auto-generated method stub
		return espacioRepository.findByTurnoAndAula(aula, turno);
	}
	
	
	public List<Espacio> traerEspacioDia(int diaSemana,List<Espacio> espacios){
		
		List<Espacio> espaciosSemana=new ArrayList<Espacio>();
		
		for (int i=0;i<espacios.size();i++) {
		
		if (espacios.get(i).getFecha().getDayOfWeek().getValue()==diaSemana) {
			
			
			espaciosSemana.add(espacios.get(i));
		}
		
		
		}
		return espaciosSemana;
	}

	@Override
	public Espacio getById(int id) {
		// TODO Auto-generated method stub
		return espacioRepository.findById(id);
	}

	@Override
	public void save(Espacio espacio) {
		espacioRepository.save(espacio);
		
	}



}