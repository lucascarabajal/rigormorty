package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cementerio;
import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ResponseEntity<Cliente> addCliente(Cliente cliente){
        Cliente newCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(newCliente);
    }


    public ResponseEntity<List<Cliente>> getClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    public Object updateCliente(Cliente cliente){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        if(optionalCliente.isPresent()){
            Cliente clienteToUpdate = optionalCliente.get();
            clienteToUpdate.setNombre(cliente.getNombre());
            clienteToUpdate.setApellido(cliente.getApellido());
            clienteToUpdate.setDni(cliente.getDni());
            clienteToUpdate.setFechaNac(cliente.getFechaNac());
            clienteToUpdate.setEmail(cliente.getEmail());
            clienteToUpdate.setTelefono(cliente.getTelefono());
            clienteToUpdate.setParcelas(cliente.getParcelas());
            clienteToUpdate.setDomicilios(cliente.getDomicilios());
            clienteToUpdate.setRegistros(cliente.getRegistros());
            clienteRepository.save(clienteToUpdate);
            return clienteToUpdate;
        }else{
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
    }


}
