package com.disenio.rigormorty.models.request;

import com.disenio.rigormorty.entity.Cliente;
import com.disenio.rigormorty.entity.Parcela;
import com.disenio.rigormorty.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RegistroCompraRequest {
    private Double entrega;
    private Integer totalCuotas;
    private Double totalPagar;
    private String formaPago;
    private Cliente cliente;
    private List<Parcela> parcelas;
    private Usuario usuario;
}
