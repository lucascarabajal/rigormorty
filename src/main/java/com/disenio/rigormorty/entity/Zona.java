package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zonas")
@Setter @Getter @NoArgsConstructor
public class Zona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreZona;

    @Column(nullable = false)
    private Integer cantidadParcela;

    @Column(nullable = false)
    private Double precioZona;

    @Column(nullable = false)
    private Integer nivelMax;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "id_zona", referencedColumnName = "id")
    @Cascade({org.hibernate.annotations.CascadeType.DELETE})
    private List<Parcela> parcelas = new ArrayList<>();

}
