package com.unla.grupo13.TrabajoPractico.services.imp;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.repositories.IAulaRepository;
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

    @Autowired
    @Qualifier("aulaRepository")
    private IAulaRepository aulaRepository;

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



    public void generarEspacioMes(String fechaInicio, String fechaFinalizacion, char turno) throws Exception {

        //int diasDelMes = funciones.diasDelMes(anio, mes);

        LocalDate inicio = LocalDate.parse(fechaInicio);

        LocalDate fin = LocalDate.parse(fechaFinalizacion);

        LocalDate aux = inicio;

        List<Aula> aulas = new ArrayList<Aula>();

        aulas = aulaRepository.findAll();


        for (Aula a: aulas) {
            System.out.println("\n" + a.toString() + "\n");
            while(aux.isBefore(fin.plusDays(1))){
                if(aux.getDayOfWeek().getValue() <= 5){
                    try {
                        this.generarEspacios(a, turno, aux, true);
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                aux = aux.plusDays(1);
            }

            aux = inicio;
        }

    }

   



//    public void generarEspacioMes(int anio, int mes, char turno, Aula aula) throws Exception {
//
//        int diasDelMes = LocalDate.of(anio,mes,1).lengthOfMonth();
//
//        for (int i = 0; i < diasDelMes; i++) {
//
//            generarEspacios(aula, turno, LocalDate.of(anio, mes, i + 1), true);
//        }
//
//    }

    public List<Espacio> getByTurno(char turno){
        List<Espacio> espacios = espacioRepository.findByTurno(turno);
        return espacios;
    }

    public List<Espacio> traerEspaciosDeAula(Aula aula , char turno, boolean libre){
        List<Espacio> espacios = espacioRepository.findByAulaAndTurnoAndLibre(aula, turno, libre);
        return espacios;
    }

    public List<Espacio> getByTurnoAndDiaSemana(char turno, int dia){
        List<Espacio> espacioTemp = espacioRepository.findByTurno(turno);
        List<Espacio> espacios = new ArrayList<Espacio>();
        for(Espacio e: espacioTemp){
            if(e.getFecha().getDayOfWeek().getValue() == dia){
                espacios.add(e);
            }
        }
        return espacios;
    }



}