package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 75)
    private String email;

    @Column(nullable = false)
    private int numTel;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private List<Roles> userRol = new ArrayList<>();

}
