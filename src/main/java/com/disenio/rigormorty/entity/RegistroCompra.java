package com.disenio.rigormorty.entity;

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
    private Date vencimiento;

    @Column
    @Temporal(TemporalType.DATE)
    private Date pago;

    @OneToMany(mappedBy = "periodos", cascade = CascadeType.ALL)
    private List<Periodo> periodos = new ArrayList<>();

    @OneToMany(mappedBy = "registro", cascade = CascadeType.ALL)
    private List<FormaPago> formaPagos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"registros", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"registros", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_cliente",nullable = false)
    private Cliente cliente;


}
