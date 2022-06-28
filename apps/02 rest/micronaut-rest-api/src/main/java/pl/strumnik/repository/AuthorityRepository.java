package pl.strumnik.repository;

import java.util.Optional;
import pl.strumnik.domain.Authority;

public interface AuthorityRepository {

  Optional<Authority> findById(String id);
}
