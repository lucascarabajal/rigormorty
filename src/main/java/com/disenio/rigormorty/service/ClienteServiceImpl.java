package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.mappers.ParcelaMapper;
import com.disenio.rigormorty.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ResponseEntity<Cliente> addCliente(Cliente cliente){
        Cliente newCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(newCliente);
    }

    @Override
    public ResponseEntity<List<Cliente>> getClientes(){
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @Override
    public Object updateCliente(Cliente cliente){
        Optional<Cliente> optionalCliente = clienteRepository.findById(cliente.getId());
        if(optionalCliente.isPresent()){
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
        }else{
            throw new ResourceNotFoundException("Cliente no encontrado");
        }
    }

    public Cliente getById(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()){
            return cliente.get();
        }else {
            throw new ResourceNotFoundException("No existe cliente con ese id");
        }
    }

}
