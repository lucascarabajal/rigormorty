package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "difuntos")
@Getter @Setter @NoArgsConstructor
public class Difunto extends Persona implements Serializable {
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDef;

    @Column(nullable = false, length = 50)
    private String numExpediente;

    @Column(nullable = false)
    private Integer numNivel;

    @Column(length = 100)
    private String acta;
}
