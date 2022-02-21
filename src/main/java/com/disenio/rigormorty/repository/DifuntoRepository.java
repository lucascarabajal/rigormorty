package com.disenio.rigormorty.Repository;

import com.disenio.rigormorty.entity.Difunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DifuntoRepository extends JpaRepository<Difunto,Long> {
}
