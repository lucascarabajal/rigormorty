package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Setter @Getter @NoArgsConstructor
public class Zona implements Serializable {

    @Id
    private String nombreZona;

    @Column(nullable = false)
    private int cantidadParcela;

    @Column(nullable = false)
    private double precioZona;

    @Column(nullable = false)
    private int nivelMax;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"domCem", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_Cementerio")
    private Cementerio zonaCem;

}
