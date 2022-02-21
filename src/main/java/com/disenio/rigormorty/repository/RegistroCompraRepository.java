package com.disenio.rigormorty.Repository;

import com.disenio.rigormorty.entity.RegistroCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroCompraRepository extends JpaRepository<RegistroCompra,Long> {
}
