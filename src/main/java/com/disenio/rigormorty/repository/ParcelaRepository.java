package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Difunto;
import com.disenio.rigormorty.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela,Long> {
    List<Parcela> getParcelasByCliente_IdAndAsignadaTrue(Long id);

    @Query( value = "SELECT * FROM parcela p inner join zonas z on p.id_zona = z.id where z.id = :zona",
            nativeQuery = true)
    List<Parcela> getParcelasByZona(@Param("zona") Long id);
    @Query( value = "SELECT * FROM parcela p inner join zonas z on p.id_zona = z.id where asignada = false and z.id = :zona",
    nativeQuery = true)
    List<Parcela> getParcelasLibres(@Param("zona") Long id);

    @Query(value = "select * from parcela inner join difuntos d on parcela.id = d.id_parcela where d.id = :id",
        nativeQuery = true)
    Parcela getParcelaByDifunto(Long id);
}
