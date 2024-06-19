package PublicityProject.PROYECTOPUBLICIDAD.repository;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {
}
