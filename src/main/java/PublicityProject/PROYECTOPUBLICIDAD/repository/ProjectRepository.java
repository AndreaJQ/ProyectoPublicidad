package PublicityProject.PROYECTOPUBLICIDAD.repository;

import PublicityProject.PROYECTOPUBLICIDAD.entity.Proyecto;
import PublicityProject.PROYECTOPUBLICIDAD.enumeration.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository <Proyecto, String> {
   /* Optional<Proyecto> findByTitle(String title);
    @Query("SELECT p from proyecto p WHERE p.nombre LIKE CONCAT('%',:query,'%')")
    List<Proyecto> searchPublication(String query);
    @Query("SELECT p from proyecto p WHERE p.notas LIKE CONCAT('%',:query,'%')")
    List<Proyecto> searchContentPublication(String query);


    List<Proyecto> findByRubro(ProjectStatus estado);

    */
}
