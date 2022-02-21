package com.disenio.rigormorty.Repository;

import com.disenio.rigormorty.entity.EstadoParcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoParcelaRepository extends JpaRepository<EstadoParcela,Long> {
}
