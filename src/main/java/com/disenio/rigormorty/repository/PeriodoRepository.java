package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Periodo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoRepository extends JpaRepository<Periodo,Long> {
}
