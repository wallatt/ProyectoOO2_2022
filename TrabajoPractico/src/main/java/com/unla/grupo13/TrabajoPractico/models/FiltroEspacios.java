package com.unla.grupo13.TrabajoPractico.models;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class FiltroEspacios {

    public Set<Aula> filtrarEspacios(Parametros parametros, List<Espacio> espaciosFiltradosPorFechaYTurno) {

        Set<Aula> aulas = new HashSet<Aula>();
        //Obtengo lista de aulas a partir de los espacios
        for (Espacio e : espaciosFiltradosPorFechaYTurno) {
            aulas.add(e.getAula());
        }
        //A cada aula le pongo sus espacios
        //y obtengo cuantos espacios hay en total por aula
        int maxEspacios = 0;
        int contador = 0;
        for (Aula a : aulas) {
            contador = 0;
            Set<Espacio> espacios = new HashSet<>();
            for (Espacio e : espaciosFiltradosPorFechaYTurno) {
                if (e.getAula().equals(a)){
                    contador += 1;
                    if(e.isLibre()){
                        espacios.add(e);
                    }
                }
                a.setEspacios(espacios);
            }
            maxEspacios = Math.max(contador, maxEspacios);
        }


        switch(parametros.getTipopresencial()) {
            case 0:
               return traerEspaciosFull(aulas, maxEspacios);
            case 1:
               return traerEspaciosMitad(aulas, maxEspacios);
            case 2:
               return traerEspaciosFinal(aulas, parametros);
            default:
                return null;
        }

    }

    public Set<Aula> traerEspaciosFull(Set<Aula> aulasList, int max){
        Set<Aula> aulasConEspaciosEnFechaTurnoYTipo = new HashSet<Aula>();
        int contadorLibre;
        for (Aula a : aulasList) {
            contadorLibre = 0;
            for (Espacio e : a.getEspacios()) {
                contadorLibre += 1;
            }
            if (contadorLibre >= max) {
                aulasConEspaciosEnFechaTurnoYTipo.add(a);
            }
        }
        return aulasConEspaciosEnFechaTurnoYTipo;
    }
    public Set<Aula> traerEspaciosFinal(Set<Aula> aulas, Parametros parametros) {
        Set<Aula> aulasConEspaciosEnFechaTurnoYTipo = new HashSet<Aula>();
        for (Aula a : aulas) {
            for (Espacio e : a.getEspacios()) {
                if (e.getFecha().equals(parametros.getFechaFinal())) {
                    Set<Espacio> espacioFinal = new HashSet<Espacio>();
                    espacioFinal.add(e);
                    a.setEspacios(espacioFinal);
                    aulasConEspaciosEnFechaTurnoYTipo.add(a);
                }
            }
        }
        return aulasConEspaciosEnFechaTurnoYTipo;

    }
    public Set<Aula> traerEspaciosMitad(Set<Aula> aulas, int maxEspacios) {

        Set<Aula> aulasConEspaciosEnFechaTurnoYTipo = new HashSet<Aula>();
        Comparator<Espacio> compararPorfecha = Comparator.comparing(Espacio::getFecha);
        List<Espacio> espaciosOrdenados;
        boolean hayPar;
        boolean hayImpar;
        LocalDate fechaAComparar;
        int dias;
        int modDiff;
        Set<Espacio> espaciosPares;
        Set<Espacio> espaciosImPares;


            for (Aula a : aulas) {
                espaciosOrdenados = a.getEspacios().stream().sorted(compararPorfecha).collect(Collectors.toList());
                hayPar = true;
                hayImpar = true;
                fechaAComparar = espaciosOrdenados.get(0).getFecha();
                espaciosPares = new HashSet<Espacio>();
                espaciosImPares = new HashSet<Espacio>();
                //habria que hacer dos listas para cada semana par e impar
                for (Espacio e : espaciosOrdenados) {
                    dias = (int) Duration.between(fechaAComparar.atStartOfDay(), e.getFecha().atStartOfDay()).toDays();
                    if (dias % 14 != 0) {
                        espaciosPares.add(e);
                    }else{
                        espaciosImPares.add(e);
                    }
                }
                if(espaciosPares.size() < maxEspacios / 2){
                    hayPar = false;

                }
                if(espaciosImPares.size() < maxEspacios /2){
                    hayImpar = false;
                }

                if(hayPar){
                    a.setEspacios(espaciosPares);
                    aulasConEspaciosEnFechaTurnoYTipo.add(a);
                }else{
                    if(hayImpar){
                        a.setEspacios(espaciosImPares);
                        aulasConEspaciosEnFechaTurnoYTipo.add(a);
                    }
                }
            }
            return aulasConEspaciosEnFechaTurnoYTipo;

        }




    }







