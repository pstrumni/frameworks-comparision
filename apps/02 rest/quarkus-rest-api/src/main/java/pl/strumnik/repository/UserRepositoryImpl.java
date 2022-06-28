package pl.strumnik.repository;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import pl.strumnik.domain.ApplicationException;
import pl.strumnik.domain.User;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

  private final Map<String, User> users = new ConcurrentHashMap<>();

  @Override
  public Set<User> findAll() {
    return new HashSet<>(users.values());
  }

  @Override
  public Optional<User> findById(final String id) {
    if (users.containsKey(id)) {
      return Optional.of(users.get(id));
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void save(final User user) {
    users.put(user.getId(), user);
  }

  @Override
  public void delete(final String id) {
    if (users.containsKey(id)) {
      users.remove(id);
    } else {
      throw new ApplicationException();
    }
  }
}
