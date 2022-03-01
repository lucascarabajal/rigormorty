package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZonaService {
    private final ZonaRepository zonaRepository;

    @Autowired
    public ZonaService(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public ResponseEntity<Zona> addZona(Zona zona){
        Zona newZona = zonaRepository.save(zona);

        return ResponseEntity.ok(newZona);
    }

    public ResponseEntity<List<Zona>> getZona(){
        List<Zona> zonas = zonaRepository.findAll();
        return ResponseEntity.ok(zonas);
    }

    public Object updateZona(Zona zona){
        Optional<Zona> optionalZona = zonaRepository.findById(zona.getNombreZona());
        if(optionalZona.isPresent()){
            Zona zonaToUpdate = optionalZona.get();

            zonaToUpdate.setPrecioZona(zona.getPrecioZona());
            zonaToUpdate.setCementerio(zona.getCementerio());
            zonaToUpdate.setCantidadParcela(zona.getCantidadParcela());
            zonaToUpdate.setNivelMax(zona.getNivelMax());
            zonaToUpdate.setParcelas(zona.getParcelas());

            zonaRepository.save(zonaToUpdate);

            return zonaToUpdate;
        }else{
            throw new ResourceNotFoundException("Zona no encontrada");
        }


    }
}
