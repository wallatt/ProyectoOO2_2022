package com.unla.grupo13.TrabajoPractico.entities;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
	@Inheritance(strategy = InheritanceType.JOINED)
	public abstract class Aula extends EntityBase{

	   
		@Column(name = "numero")
	    protected int numero;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "edificio_id", nullable = false)
	    protected Edificio edificio;
	   
	    @OneToMany(fetch=FetchType.LAZY, mappedBy="aula")
		protected Set<Espacio> espacios=new HashSet<Espacio>();
		
	    
	    public Set<Espacio> getEspacios() {
			return espacios;
		}


		public void setEspacios(Set<Espacio> espacios) {
			this.espacios = espacios;
		}


		public Aula() {}


		public int getNumero() {
			return numero;
		}


		public void setNumero(int numero) {
			this.numero = numero;
		}


		public Edificio getEdificio() {
			return edificio;
		}


		public void setEdificio(Edificio edificio) {
			this.edificio = edificio;
		}
	    
	    
	    
	}
    
    
    

