package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;

@Repository("notaPedidoRepository")
public interface INotaPedidoRepository extends JpaRepository<NotaPedido, Integer>{

    @Query("SELECT np FROM NotaPedido np JOIN FETCH np.materia WHERE np.id=(:id)")
    public abstract NotaPedido findById(int id);


}
