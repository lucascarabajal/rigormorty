package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.exception.CustomException;
import com.disenio.rigormorty.models.request.PagarCuotaRequest;
import com.disenio.rigormorty.models.responses.CuotaAllResponse;
import com.disenio.rigormorty.models.responses.CuotaResponse;
import com.disenio.rigormorty.repository.CuotaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CuotaServiceImpl implements CuotaService{

    private final CuotaRepository cuotaRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseEntity<Object> add(Cuota cuota) {
        return ResponseEntity.ok().body(cuotaRepository.save(cuota));
    }

    @Override
    public List<CuotaAllResponse> getCuotas(){
        return cuotaRepository.findAll().stream().map(cuota -> this.mapper.map(cuota, CuotaAllResponse.class)).collect(Collectors.toList());
    }

    @Override
    public List<CuotaAllResponse> getCuotasByCliente(Integer dniCliente){
        return cuotaRepository.findAllByRegistroCompraClienteDni(dniCliente).stream().map(cuota -> this.mapper.map(cuota, CuotaAllResponse.class)).collect(Collectors.toList());
    }

    @Override
    public Cuota pagoCuota(PagarCuotaRequest cuotaRequest) {

        Cuota cuotaAnterior = cuotaRepository.findFirstByRegistroCompraIdOrderByIdDesc(cuotaRequest.getRegistroCompra().getId());

        Cuota cuota = new Cuota();

        Integer cantidadCuota = cuotaAnterior.getTotalCuotasPagas() + cuotaRequest.getCantCuota();

        if (cuotaAnterior.getTotalCuotasPagas() == 0){
            cuota.setTotalCuotasPagas(cuotaRequest.getCantCuota());
        } else if (cuotaAnterior.getTotalCuotasPagas().equals(cuotaAnterior.getTotalCuotas())){
            throw new CustomException("Usted ya pago todas las cuotas de este registro");
        }else if (cuotaAnterior.getTotalCuotasPagas()>0 && cantidadCuota <= cuotaAnterior.getTotalCuotas() ) {
            cuota.setTotalCuotasPagas(cuotaAnterior.getTotalCuotasPagas()+cuotaRequest.getCantCuota());
        }else {
            throw new CustomException("Usted esta queriendo pagar más cuotas de las que debe");
        }

        cuota.setCantCuota(cuotaRequest.getCantCuota());
        cuota.setFechaPago(LocalDate.now());
        cuota.setFechaVencimiento(getVencimiento(cuotaRequest.getCantCuota()));
        cuota.setPago(cuotaRequest.getPago());
        cuota.setRegistroCompra(cuotaRequest.getRegistroCompra());
        cuota.setTotalCuotas(cuotaAnterior.getTotalCuotas());

        return cuotaRepository.save(cuota);
    }

    private LocalDate getVencimiento(Integer cantidad) {
        LocalDate localDate = LocalDate.now();
        return localDate.plusMonths(cantidad);
    }

}
