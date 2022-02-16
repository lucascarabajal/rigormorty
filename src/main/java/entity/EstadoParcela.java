package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.NombreParcela;

import javax.persistence.*;

@Entity
@Table(name = "estadoParcela")
public class EstadoParcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombreParcela estadoParcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"parcelas", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcelas;

}
