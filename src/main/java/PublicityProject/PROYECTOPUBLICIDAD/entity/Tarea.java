package PublicityProject.PROYECTOPUBLICIDAD.entity;


import PublicityProject.PROYECTOPUBLICIDAD.enumeration.estadoTarea;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Data
@Table(name = "tarea")
@Entity
public class Tarea {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name ="uuid", strategy = "uuid2")
    private String idTarea;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private estadoTarea estadoTarea;
    @ManyToOne
    private Proyecto proyecto;
    @ManyToOne
    private UserEntity agente;
    private Boolean baja;



}
