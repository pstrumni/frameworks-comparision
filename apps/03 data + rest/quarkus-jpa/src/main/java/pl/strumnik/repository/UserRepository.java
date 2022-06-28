package pl.strumnik.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import org.hibernate.annotations.QueryHints;
import pl.strumnik.domain.User;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<User, String> {

  public Optional<User> findByIdWithAddressesAndAuthorities(String id) {
    final List<User> resultList = getEntityManager().createQuery("SELECT DISTINCT u FROM User u "
            + "LEFT JOIN FETCH u.addresses "
            + "LEFT JOIN FETCH u.authorities "
            + "WHERE u.id = :id", User.class)
        .setParameter("id", id)
        .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
        .getResultList();

    return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
  }
}
