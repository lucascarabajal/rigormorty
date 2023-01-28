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
    private String numeroParcela;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcela", referencedColumnName = "id")
    private List<Difunto> difuntos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcela", referencedColumnName = "id")
    private List<EstadoParcela> estados = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcela", referencedColumnName = "id")
    private List<Mantenimiento> mantenimientos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcela", referencedColumnName = "id")
    private List<RegistroCompra> registros = new ArrayList<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonIgnoreProperties(value = {"parcelas", "handler","hibernateLazyInitializer"}, allowSetters = true)
//    @JoinColumn(name = "id_cliente")
//    private Cliente cliente;

}
