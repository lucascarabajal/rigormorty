package com.disenio.rigormorty.Repository;

import com.disenio.rigormorty.entity.Cementerio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CementerioRepository extends JpaRepository<Cementerio,Long> {
}
