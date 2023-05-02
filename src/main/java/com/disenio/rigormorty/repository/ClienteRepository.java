package com.disenio.rigormorty.repository;

import com.disenio.rigormorty.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente getById(Long id);
    Cliente getClienteByDni(Integer dni);

    @Query(value = "select clientes.id, apellido, dni, fecha_nac, nombre, email, telefono from clientes inner join parcela p on clientes.id = p.id_cliente where p.asignada=true group by clientes.id, apellido, dni, fecha_nac, nombre, email, telefono", nativeQuery = true)
    List<Cliente> getClientesWithParcela();
}
