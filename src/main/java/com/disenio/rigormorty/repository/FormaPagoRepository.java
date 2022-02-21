package com.disenio.rigormorty.Repository;

import com.disenio.rigormorty.entity.FormaPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagoRepository extends JpaRepository<FormaPago,Long> {
}
