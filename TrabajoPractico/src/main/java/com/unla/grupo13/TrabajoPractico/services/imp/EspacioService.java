package com.unla.grupo13.TrabajoPractico.services.imp;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.repositories.IEspacioRepository;
import com.unla.grupo13.TrabajoPractico.services.IEspacioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

        e = new Espacio(aula, turno, fecha, libre);

        return espacioRepository.save(e);
    }

    public void generarEspacioMes(int anio, int mes, char turno, Aula aula) throws Exception {

        int diasDelMes = LocalDate.of(anio,mes,1).lengthOfMonth();

        for (int i = 0; i < diasDelMes; i++) {

            generarEspacios(aula, turno, LocalDate.of(anio, mes, i + 1), true);
        }

    }

    public List<Espacio> getByTurno(char turno){
        List<Espacio> espacios = espacioRepository.findByTurno(turno);
        return espacios;
    }

    public List<Espacio> traerEspaciosDeAula(Aula aula , char turno, boolean libre){
        List<Espacio> espacios = espacioRepository.findByAulaAndTurnoAndLibre(aula, turno, libre);
        return espacios;
    }



}