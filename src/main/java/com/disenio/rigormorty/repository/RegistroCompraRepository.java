package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.RegistroCompra;
import com.disenio.rigormorty.models.responses.RegistroStatsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroCompraRepository extends JpaRepository<RegistroCompra,Long> {

    List<RegistroCompra> getRegistroComprasByClienteDni(Integer dni);
    List<RegistroCompra> findAllByUsuarioId(Long id);
}
