package com.unla.grupo13.TrabajoPractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Aula extends EntityBase{

    @Column(name = "numero")
    protected int numero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edificio_id", nullable = false)
    protected Edificio edificio;

    public Aula(int numero, boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion){
        super(softDelete, fechaCreacion, fechaModificacion);
        this.numero = numero;
    }
}
