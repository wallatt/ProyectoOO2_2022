package com.unla.grupo13.TrabajoPractico.repositories;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import com.unla.grupo13.TrabajoPractico.entities.Laboratorio;
import com.unla.grupo13.TrabajoPractico.entities.Espacio;
import com.unla.grupo13.TrabajoPractico.entities.Aula;


@Repository("espacioRepository")
public interface IEspacioRepository extends JpaRepository<Espacio, Serializable>{
    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a WHERE e.turno=(:turno) and e.fecha=(:fecha) and a=(:aula)")
    public abstract Espacio findByLibreFechaAula(@Param("aula") Aula aula, @Param("turno")char turno, @Param ("fecha") LocalDate fecha);
    Espacio save(Espacio e);
    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a JOIN FETCH a.edificio WHERE e.libre=(:libre) and e.turno=(:turno) and a.cantSillas>=(:cantSillas) GROUP BY e.aula")
    public abstract List<Laboratorio> findByLaboratorioLibre(@Param("libre") boolean libre, @Param("turno") char turno, @Param("cantSillas") int cantSillas);
    @Query("SELECT e FROM Espacio e JOIN FETCH e.aula a WHERE e.turno=(:turno) and a=(:aula) and e.libre=(:libre)" )
    public abstract List<Espacio> findByTurnoAndAula(@Param("aula") Aula aula, @Param("turno")char turno, @Param("libre")boolean libre);

    public abstract List<Espacio> findByAulaAndTurnoAndLibre(Aula aula, char turno, boolean libre);

    public abstract List<Espacio> findByTurno(char turno);



}