package PublicityProject.PROYECTOPUBLICIDAD.entity;
import java.util.Date;


import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(generator = "uuid")

    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(columnDefinition = "LONGTEXT")
    private String contenido;
    @ManyToOne
    private UserEntity usuario;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fecha;
}
