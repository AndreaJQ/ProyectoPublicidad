package PublicityProject.PROYECTOPUBLICIDAD.repository;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}
