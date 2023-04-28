package com.disenio.rigormorty.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonMerge;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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
    private Double totalPagar;

    @Column
    @Temporal(TemporalType.DATE)
    private Date vencimiento;

    @Column
    @Temporal(TemporalType.DATE)
    private Date pago;

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

}
