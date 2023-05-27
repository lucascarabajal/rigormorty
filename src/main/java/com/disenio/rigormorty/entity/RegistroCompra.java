package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "registro_compra")
@Getter @Setter @NoArgsConstructor
public class RegistroCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double entrega;

    @Column
    private Double totalPagar;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDate fechaCompra;

    @Column
    private String formaPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente",nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_registro",referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JsonMerge
    private List<Parcela> parcelas;

    @ManyToOne(optional=false)
    @JoinColumn(name="usuario",referencedColumnName="id")
    private Usuario usuario;

    @OneToMany(mappedBy = "registroCompra")
    private List<Cuota> cuotas = new ArrayList<>();

}
