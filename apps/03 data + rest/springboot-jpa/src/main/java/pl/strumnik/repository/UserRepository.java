package pl.strumnik.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.strumnik.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  @EntityGraph(attributePaths = {"authorities", "addresses"})
  Optional<User> findById(String id);
}
