package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela,Long> {
    List<Parcela> getParcelasByCliente_Id(Long id);

    @Query( value = "SELECT * FROM parcela p inner join zonas z on p.id_zona = z.id where id_cliente is null and z.id = :zona",
    nativeQuery = true)
    List<Parcela> getParcelasLibres(@Param("zona") Long id);
}
