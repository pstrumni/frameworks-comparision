package pl.strumnik.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import io.micronaut.core.annotation.ReflectiveAccess;
import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect(
    getterVisibility = Visibility.NONE,
    isGetterVisibility = Visibility.NONE,
    setterVisibility = Visibility.NONE,
    creatorVisibility = Visibility.NONE,
    fieldVisibility = Visibility.ANY
)
@ReflectiveAccess
public abstract class BaseDomainObject {

  private String id;

  protected BaseDomainObject(final String id) {
    this.id = id;
  }

  protected BaseDomainObject() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    } else if (o instanceof BaseDomainObject) {
      final BaseDomainObject other = (BaseDomainObject) o;
      return getId().equals(other.getId());
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
