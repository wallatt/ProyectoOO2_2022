package com.unla.grupo13.TrabajoPractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @NoArgsConstructor
public class EntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "softDelete")
    protected boolean softDelete = Boolean.TRUE;

    @Column(name = "fechaCreacion")
    @CreationTimestamp
    protected LocalDateTime fechaCreacion;

    @Column(name = "fechaModificacion")
    @UpdateTimestamp
    protected LocalDateTime fechaModificacion;

    public EntityBase(boolean softDelete, LocalDateTime fechaCreacion, LocalDateTime fechaModificacion) {
        this.softDelete = softDelete;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }
}
