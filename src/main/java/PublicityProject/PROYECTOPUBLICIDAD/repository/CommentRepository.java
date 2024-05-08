package PublicityProject.PROYECTOPUBLICIDAD.repository;
import PublicityProject.PROYECTOPUBLICIDAD.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}