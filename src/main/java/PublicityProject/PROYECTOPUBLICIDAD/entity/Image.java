package PublicityProject.PROYECTOPUBLICIDAD.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Table(name = "imagen")
@Entity
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String nombre;
    private String tipo;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;
}
