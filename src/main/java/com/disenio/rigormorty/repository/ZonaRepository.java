package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Zona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZonaRepository extends JpaRepository<Zona,String> {

}
