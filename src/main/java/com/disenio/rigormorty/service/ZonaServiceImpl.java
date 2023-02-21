package com.disenio.rigormorty.service;


import com.disenio.rigormorty.entity.EstadoParcela;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Usuario;
import com.disenio.rigormorty.entity.Zona;
import com.disenio.rigormorty.enums.NombreParcela;
import com.disenio.rigormorty.exception.EqualObjectException;
import com.disenio.rigormorty.exception.ResourceNotFoundException;
import com.disenio.rigormorty.models.responses.ZonaResponse;
import com.disenio.rigormorty.repository.ParcelaRepository;
import com.disenio.rigormorty.repository.ZonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ZonaServiceImpl implements ZonaService{
    private final ZonaRepository zonaRepository;

    @Autowired
    public ZonaServiceImpl(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    @Override
    public ResponseEntity<Zona> addZona(Zona zona) {

        if (Objects.nonNull(zonaRepository.findZonaByNombreZona(zona.getNombreZona()))) throw new EqualObjectException("Ya existe zona con este nombre");

        zona.setParcelas(generarParcelas(zona));

        Zona newZona = zonaRepository.save(zona);
        return ResponseEntity.ok(newZona);
    }

    @Override
    public ResponseEntity<List<Zona>> getZonas(){
        List<Zona> zonas = zonaRepository.findAll();
        return ResponseEntity.ok(zonas);
    }

    @Override
    public Object updateZona(Zona zona){
        Optional<Zona> optionalZona = zonaRepository.findById(zona.getId());
        if(optionalZona.isPresent()){
            Zona zonaToUpdate = optionalZona.get();

            zonaToUpdate.setPrecioZona(zona.getPrecioZona());
            zonaToUpdate.setCantidadParcela(zona.getCantidadParcela());
            zonaToUpdate.setNivelMax(zona.getNivelMax());
            zonaToUpdate.setParcelas(zona.getParcelas());

            zonaRepository.save(zonaToUpdate);

            return zonaToUpdate;
        }else{
            throw new ResourceNotFoundException("Zona no encontrada");
        }
    }

    @Override
    public void addListZona(List<Zona> zonas){
        for (int i=0; i < zonas.size(); i++){
            zonas.get(i).setParcelas(generarParcelas(zonas.get(i)));
        }
        zonaRepository.saveAll(zonas);
    }

    @Override
    public ZonaResponse findById(Long id) {
        Optional<Zona> zona = zonaRepository.findById(id);

        if (zona.isPresent()){
            return zonaRepository.findZonaByNombreZona(zona.get().getNombreZona());
        }else{
            throw new ResourceNotFoundException("No se encuentra zona con ese id");
        }
    }

    private List<Parcela> generarParcelas(Zona zona){
        List<Parcela> parcelas = IntStream.range(0, zona.getCantidadParcela())
                .mapToObj(i ->{
                    Parcela parcela = new Parcela();
                    List<EstadoParcela> estadoParcelas = new ArrayList<>();

                    parcela.setNumeroParcela(zona.getNombreZona()+(i+1));
                    parcela.setNivelMax(zona.getNivelMax());

                    IntStream.range(0, zona.getNivelMax()).forEach(j -> {
                        EstadoParcela estadoParcela = new EstadoParcela();
                        estadoParcela.setEstadoParcela(NombreParcela.ESTADO_PARCELA_LIBRE);
                        estadoParcelas.add(estadoParcela);
                    });

                    parcela.setEstados(estadoParcelas);
                    return parcela;
                })
                .collect(Collectors.toList());

        return parcelas;
    }
}
