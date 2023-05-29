package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Mantenimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MantenimientoRepository extends JpaRepository<Mantenimiento,Long> {

    List<Mantenimiento> getMantenimientoByCliente_Id(Long id);

    List<Mantenimiento> getMantenimientoByParcela_Id(Long id);

    @Query(value = "select m from Mantenimiento m "+
            "left join Mantenimiento m2 on m.parcela.id = m2.parcela.id and m.id < m2.id "+
            "where m2.id is null "+
            "order by m.parcela.id")
    List<Mantenimiento> findMantenimientosWithoutDuplicates();
}
