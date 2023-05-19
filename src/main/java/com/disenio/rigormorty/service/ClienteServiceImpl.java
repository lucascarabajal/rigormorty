package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.request.ClienteAddRequest;
import com.disenio.rigormorty.models.responses.ClienteAddResponse;
import com.disenio.rigormorty.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<ClienteAddResponse> addCliente(ClienteAddRequest clienteRequest) {

        if (Objects.nonNull(clienteRepository.getClienteByDni(clienteRequest.getDni())))
            throw new EqualObjectException("El cliente con el dni " + clienteRequest.getDni() + " ya existe.");

        Cliente cliente = clienteRepository.save(this.mapper.map(clienteRequest, Cliente.class));
        ClienteAddResponse response = this.mapper.map(cliente, ClienteAddResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<List<ClienteAddResponse>> getClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteAddResponse> responses = clientes.stream().map(cliente -> this.mapper.map(cliente, ClienteAddResponse.class)).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responses);
    }

    @Override
    public Object updateCliente(Cliente cliente) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        if (optionalCliente.isPresent()) {
            Cliente clienteToUpdate = optionalCliente.get();
            clienteToUpdate.setId(cliente.getId());
            clienteToUpdate.setNombre(cliente.getNombre());
            clienteToUpdate.setApellido(cliente.getApellido());
            clienteToUpdate.setDni(cliente.getDni());
            clienteToUpdate.setFechaNac(cliente.getFechaNac());
            clienteToUpdate.setEmail(cliente.getEmail());
            clienteToUpdate.setTelefono(cliente.getTelefono());
            clienteToUpdate.setDomicilios(cliente.getDomicilios());
            clienteToUpdate.setRegistros(cliente.getRegistros());
            clienteRepository.save(clienteToUpdate);
            return clienteToUpdate;
        } else {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
    }

    public ClienteAddResponse getById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            return this.mapper.map(cliente.get(), ClienteAddResponse.class);
        } else {
            throw new ResourceNotFoundException("No existe cliente con ese id");
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        try {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().body("Se borró correctamente") ;
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("El cliente tiene una parcela a su nombre");
        }
    }

    @Override
    public List<ClienteAddResponse> clientesWithParcelas() {
        return clienteRepository.getClientesWithParcela().stream().map(cliente -> this.mapper.map(cliente,ClienteAddResponse.class)).collect(Collectors.toList());
    }
}