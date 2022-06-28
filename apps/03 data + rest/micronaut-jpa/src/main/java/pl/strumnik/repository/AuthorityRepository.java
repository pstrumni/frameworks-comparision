package pl.strumnik.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;
import pl.strumnik.domain.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
