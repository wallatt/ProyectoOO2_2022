package com.unla.grupo13.TrabajoPractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor
@Entity
public class Tradicional extends Aula{

    @Column(name = "cantBancos")
    private int cantBancos;

    @Column(name = "pizarron")
    private String pizarron;

    @Column(name = "tieneproyector")
    private boolean tieneProyector;

   
}
