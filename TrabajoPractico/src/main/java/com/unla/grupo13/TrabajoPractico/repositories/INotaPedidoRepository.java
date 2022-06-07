package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo13.TrabajoPractico.entities.NotaPedido;

@Repository("notaPedidoRepository")
public interface INotaPedidoRepository extends JpaRepository<NotaPedido, Serializable>{

	public abstract NotaPedido findById(int id);
	
	@Query("SELECT n from NotaPedido n where n.codCurso =(:codCurso)")
	public abstract List<NotaPedido> findByCodCurso(String codCurso);
	
	
}
