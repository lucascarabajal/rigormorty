package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter @Setter @NoArgsConstructor
public class Cliente extends Persona implements Serializable {
    @Column(nullable = false,length = 75)
    private String email;

    @Column(nullable = false)
    private int numTel;

    @OneToMany(mappedBy = "parcelas", cascade = CascadeType.ALL)
    private List<Parcela> parcelas = new ArrayList<>();

    @OneToMany(mappedBy = "registros", cascade = CascadeType.ALL)
    private List<RegistroCompra> registros = new ArrayList<>();

}
