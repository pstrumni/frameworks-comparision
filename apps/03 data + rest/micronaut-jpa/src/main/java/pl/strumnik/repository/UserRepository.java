package pl.strumnik.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.jpa.repository.JpaRepository;
import java.util.Optional;
import pl.strumnik.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  @EntityGraph(attributePaths = {"authorities", "addresses"})
  Optional<User> findById(String id);
}
