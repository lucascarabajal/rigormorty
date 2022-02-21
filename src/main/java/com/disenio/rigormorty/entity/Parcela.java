package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcela")
@Getter @Setter @NoArgsConstructor
public class Parcela implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer nivelActual;

    @Column(nullable = false)
    private Integer numeroParcela;

    @OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL)
    private List<Difunto> difuntos = new ArrayList<>();

    @OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL)
    private List<EstadoParcela> estados = new ArrayList<>();

    @OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL)
    private List<Mantenimiento> mantenimientos = new ArrayList<>();

    @OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL)
    private List<RegistroCompra> registros = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"parcelas", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_cliente",nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"parcelas", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_zona")
    private Zona zona;
}
