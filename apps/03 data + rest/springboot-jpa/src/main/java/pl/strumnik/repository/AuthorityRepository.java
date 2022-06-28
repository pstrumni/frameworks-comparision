package pl.strumnik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.strumnik.domain.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
