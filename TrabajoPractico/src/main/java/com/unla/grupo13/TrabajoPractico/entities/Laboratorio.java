package com.unla.grupo13.TrabajoPractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "laboratorio")
@Getter @Setter @NoArgsConstructor
public class Laboratorio extends Aula{

    @Column(name = "cantpc")
    private int cantPc;

    @Column(name = "cantsillas")
    private int cantSillas;

    public Laboratorio(int numero, boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion,
                       int cantPc, int cantSillas) {
        super(numero, softDelete, fechaCreacion, fechaModificacion);
        this.cantPc = cantPc;
        this.cantSillas = cantSillas;
    }
}
