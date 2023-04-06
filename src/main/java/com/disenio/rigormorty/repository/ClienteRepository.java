package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente getById(Long id);

    Cliente getClienteByDni(Integer dni);
}
