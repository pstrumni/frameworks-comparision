package pl.strumnik.domain;

import io.micronaut.core.annotation.ReflectiveAccess;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@ReflectiveAccess
@Table(name = "authorities")
@Entity
public class Authority extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @Deprecated
  protected Authority() {
    /* Required by Hibernate */
  }
}
