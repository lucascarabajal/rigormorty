package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento,Long> {

    public List<Mantenimiento> getMantenimientoByCliente_Id(Long id);
}
