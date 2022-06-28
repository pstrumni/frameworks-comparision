package pl.strumnik.repository;

import java.util.Optional;
import java.util.Set;
import pl.strumnik.domain.User;


public interface UserRepository {

  Set<User> findAll();

  Optional<User> findById(String id);

  void save(User user);

  void delete(String id);
}
