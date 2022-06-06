package com.unla.grupo13.TrabajoPractico.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("espacioRepository")
public interface IEspacioRepository extends JpaRepository<Espacio, Serializable>{

    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a WHERE e.turno=(:turno) and e.fecha=(:fecha) and a=(:aula)")
    public abstract Espacio findByLibreFechaAula(@Param("aula") Aula aula, @Param("turno")char turno, @Param ("fecha") LocalDate fecha);

    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a WHERE e.turno=(:turno) and a=(:aula) and e.libre=(:libre)" )
    public abstract List<Espacio> findByTurnoAndAula(@Param("aula") Aula aula, @Param("turno")char turno, @Param("libre")boolean libre);

    public abstract List<Espacio> findByAulaAndTurnoAndLibre(Aula aula, char turno, boolean libre);

    public abstract List<Espacio> findByTurno(char turno);



}