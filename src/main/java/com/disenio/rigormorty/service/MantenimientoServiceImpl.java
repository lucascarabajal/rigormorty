package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Mantenimiento;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.MantenimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MantenimientoServiceImpl implements MantenimientoService {
    private final MantenimientoRepository mantenimientoRepository;

    @Autowired
    public MantenimientoServiceImpl(MantenimientoRepository mantenimientoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
    }

    @Override
    public ResponseEntity<Mantenimiento> addMantenimiento(Mantenimiento mantenimiento){
        Mantenimiento newMantenimiento = mantenimientoRepository.save(mantenimiento);

        return ResponseEntity.ok(newMantenimiento);
    }

    @Override
    public ResponseEntity<List<Mantenimiento>> getMantenimientos(){
        List<Mantenimiento> mantenimientos = mantenimientoRepository.findAll();
        return ResponseEntity.ok(mantenimientos);
    }

    @Override
    public Object updateMantenimiento(Mantenimiento mantenimiento){
        Optional<Mantenimiento> optionalMantenimiento = mantenimientoRepository.findById(mantenimiento.getId());
        if(optionalMantenimiento.isPresent()){
            Mantenimiento mantenimientoToUpdate = optionalMantenimiento.get();

            mantenimientoToUpdate.setVencimiento(mantenimiento.getVencimiento());
            mantenimientoToUpdate.setPago(mantenimiento.getPago());
            mantenimientoToUpdate.setPrecio(mantenimiento.getPrecio());
            mantenimientoToUpdate.setPeriodos(mantenimiento.getPeriodos());
//            mantenimientoToUpdate.setParcela(mantenimiento.getParcela());

            mantenimientoRepository.save(mantenimientoToUpdate);

            return mantenimientoToUpdate;
        }else{
            throw new ResourceNotFoundException("Mantenimiento no encontrado");
        }


    }
}
