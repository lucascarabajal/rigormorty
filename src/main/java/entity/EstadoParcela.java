package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.NombreParcela;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "estado_parcela")
@Getter @Setter @NoArgsConstructor
public class EstadoParcela {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NombreParcela estadoParcela;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"estados", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_parcela",nullable = false)
    private Parcela parcela;

}
