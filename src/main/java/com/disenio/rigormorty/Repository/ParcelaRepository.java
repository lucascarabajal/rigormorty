package com.disenio.rigormorty.Repository;

import com.disenio.rigormorty.entity.Parcela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelaRepository extends JpaRepository<Parcela,Long> {
}
