package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import enums.NombreRol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @NoArgsConstructor
public class Roles implements Serializable {

    @Id
    @GeneratedValue
    private Long idRol;

    @Enumerated(EnumType.STRING)
    private NombreRol nombreRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"userRol", "handler","hibernateLazyInitializer"}, allowSetters = true)
    @JoinColumn(name = "id_usuario",nullable = false)
    private Usuario userRol;

}
