package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "registro_compra")
@Getter @Setter @NoArgsConstructor
public class RegistroCompra implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double entrega;

    @Column
    private Integer totalCuotas;

    @Column
    private Integer cuotasPagas;

    @Column
    @Temporal(TemporalType.DATE)
    private Date vencimientoR;

    @Column
    @Temporal(TemporalType.DATE)
    private Date pagoR;

    @OneToMany(mappedBy = "periodos", cascade = CascadeType.ALL)
    private List<Periodo> periodos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"parcelas", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcelas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"clientes", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_cliente",nullable = false)
    private Cliente clientes;


}
