package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @NoArgsConstructor
public class Cliente extends Persona implements Serializable {

    @Column(nullable = false,length = 75)
    private String email;

    @Column(nullable = false)
    private int numTel;

}
