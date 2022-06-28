package pl.strumnik.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.ApplicationScoped;
import pl.strumnik.domain.Authority;

@ApplicationScoped
public class AuthorityRepository implements PanacheRepositoryBase<Authority, String> {

}
