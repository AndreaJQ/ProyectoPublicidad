package PublicityProject.PROYECTOPUBLICIDAD.entity;


import PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
@Data
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Tarea {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private long idTarea;
    private String tarea;
    @Enumerated(EnumType.STRING)
    private estadoTarea estadoTarea;
    @ManyToOne
    private Proyecto proyecto;
    @ManyToMany
    private UserEntity agente;




}
