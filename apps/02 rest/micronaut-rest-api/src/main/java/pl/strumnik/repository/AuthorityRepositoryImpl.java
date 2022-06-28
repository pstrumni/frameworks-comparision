package pl.strumnik.repository;

import jakarta.inject.Singleton;
import java.util.Map;
import java.util.Optional;
import pl.strumnik.domain.Authority;

@Singleton
public class AuthorityRepositoryImpl implements AuthorityRepository {

  private final Map<String, Authority> authorities = Map.of(
      "1", new Authority("1", "Użytkownik", "Użytkownik"),
      "2", new Authority("2", "Administrator", "Administrator")
  );

  @Override
  public Optional<Authority> findById(final String id) {
    if (authorities.containsKey(id)) {
      return Optional.of(authorities.get(id));
    } else {
      return Optional.empty();
    }
  }
}
