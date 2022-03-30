package allMySons.allMySons.Repository;

import allMySons.allMySons.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
}
