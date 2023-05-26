package com.disenio.rigormorty.service;

import com.disenio.rigormorty.entity.Cuota;
import com.disenio.rigormorty.exception.CustomException;
import com.disenio.rigormorty.models.request.PagarCuotaRequest;
import com.disenio.rigormorty.repository.CuotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class CuotaServiceImpl implements CuotaService{

    private final CuotaRepository cuotaRepository;

    @Override
    public ResponseEntity<Object> add(Cuota cuota) {
        return ResponseEntity.ok().body(cuotaRepository.save(cuota));
    }

    @Override
    public Cuota pagoCuota(PagarCuotaRequest cuotaRequest) {

        Cuota cuotaAnterior = cuotaRepository.findFirstByRegistroCompraIdOrderByIdDesc(cuotaRequest.getRegistroCompra().getId());

        Cuota cuota = new Cuota();

        if (cuotaAnterior.getTotalCuotasPagas() == 0){
            cuota.setTotalCuotasPagas(cuotaRequest.getCantCuota());
        } else if (cuotaAnterior.getTotalCuotasPagas().equals(cuotaRequest.getRegistroCompra().getTotalCuotas())){
            throw new CustomException("Usted ya pago todas las cuotas de este registro");
        }else if (cuotaAnterior.getTotalCuotasPagas()>0) {
            cuota.setTotalCuotasPagas(cuotaAnterior.getTotalCuotasPagas()+cuotaRequest.getCantCuota());
        }

        cuota.setCantCuota(cuotaRequest.getCantCuota());
        cuota.setFechaPago(LocalDate.now());
        cuota.setFechaVencimiento(getVencimiento(cuotaRequest.getCantCuota()));
        cuota.setPago(cuotaRequest.getPago());
        cuota.setRegistroCompra(cuotaRequest.getRegistroCompra());

        return cuotaRepository.save(cuota);
    }

    private LocalDate getVencimiento(Integer cantidad) {
        LocalDate localDate = LocalDate.now();
        return localDate.plusMonths(cantidad);
    }

}
