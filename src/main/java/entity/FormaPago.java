package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.NombreForma;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "formaPago")
@Getter @Setter @NoArgsConstructor
public class FormaPago implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombreForma formaPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"registros", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_registro",nullable = false)
    private RegistroCompra registros;
}
