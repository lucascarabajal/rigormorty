package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import org.hibernate.annotations.CascadeType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parcela")
@Getter @Setter @NoArgsConstructor
public class Parcela{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer nivelMax;

    @Column(nullable = false)
    private String numeroParcela;

    @Column(nullable = false)
    private Boolean asignada;

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parcela", referencedColumnName = "id")
    private List<Difunto> difuntos = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "id_parcela", referencedColumnName = "id")
    @Cascade(CascadeType.ALL)
    private List<EstadoParcela> estados = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @JsonMerge
    private Cliente cliente;

}
