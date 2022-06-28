package pl.strumnik.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import io.micronaut.core.annotation.ReflectiveAccess;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.Hibernate;

@ReflectiveAccess
@JsonAutoDetect(
    getterVisibility = Visibility.NONE,
    isGetterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE,
    creatorVisibility = Visibility.NONE,
    fieldVisibility = Visibility.ANY
)
@MappedSuperclass
public abstract class BaseEntity {

  @Id
  private String id;

  @Deprecated
  protected BaseEntity() {
    /* Required by Hibernate */
  }

  protected BaseEntity(final String id) {
    this.id = id;
  }

  protected BaseEntity(final NonDefaultConstructorArgument nonDefaultConstructorArgument) {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    } else if (o instanceof BaseEntity) {
      final BaseEntity other = (BaseEntity) o;
      final Class<?> thisClass = Hibernate.getClass(this);
      final Class<?> otherClass = Hibernate.getClass(o);

      return getId().equals(other.getId()) && thisClass.equals(otherClass);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  protected static enum NonDefaultConstructorArgument {
    INSTANCE;
  }
}
