package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.NombrePago;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "periodo")
@Getter @Setter @NoArgsConstructor
public class Periodo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombrePago pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"periodos", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_mantenimiento",nullable = false)
    private Mantenimiento mantenimiento;


}
