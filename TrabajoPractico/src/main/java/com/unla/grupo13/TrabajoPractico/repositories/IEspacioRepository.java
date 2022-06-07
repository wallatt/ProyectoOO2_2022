package com.unla.grupo13.TrabajoPractico.repositories;

import com.unla.grupo13.TrabajoPractico.entities.Aula;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository("espacioRepository")
public interface IEspacioRepository extends JpaRepository<Espacio, Integer> {

    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a WHERE e.turno=(:turno) and e.fecha=(:fecha) and a=(:aula)")
    public abstract Espacio findByLibreFechaAula(@Param("aula") Aula aula, @Param("turno")char turno, @Param ("fecha") LocalDate fecha);


    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a JOIN FETCH a.edificio WHERE e.libre=(:libre) and e.turno=(:turno) and a.cantSillas>=(:cantSillas) GROUP BY e.aula")
    public abstract List<Laboratorio> findByLaboratorioLibre(@Param("libre") boolean libre, @Param("turno") char turno, @Param("cantSillas") int cantSillas);

    public abstract List<Espacio> findByTurno(char turno);

    public abstract Espacio findById(int id);


	public abstract List<Espacio> findByTurnoAndAula(Aula aula, char turno);

}
