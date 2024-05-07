package PublicityProject.PROYECTOPUBLICIDAD.repository;

import PublicityProject.PROYECTOPUBLICIDAD.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //UserEntity findFirstByUsername(String username);

    UserEntity findByEmail(String email);
}
