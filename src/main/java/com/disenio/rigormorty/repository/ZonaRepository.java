package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.models.responses.ZonaResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZonaRepository extends JpaRepository<Zona,Long> {

    ZonaResponse findZonaByNombreZona(String nombreZona);

}
