package PublicityProject.PROYECTOPUBLICIDAD.entity;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.Role;
import javax.persistence.*;
import lombok.*;

@Data
@Table(name = "usuarios")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String contact;
    private String address; //telefono
    @OneToOne
    private Imagen image;

    private boolean status;//anotacion para dar de baja
    @Enumerated(EnumType.STRING)
    private Role role;



}
