package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuotaRepository extends JpaRepository<Cuota,Long> {
    Cuota findFirstByRegistroCompraIdOrderByIdDesc(Long idRegistroCompra);

    List<Cuota> findAllByRegistroCompraClienteDni(Integer dni);
}
