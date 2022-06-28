package pl.strumnik.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.ReflectiveAccess;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@ReflectiveAccess
@Table(name = "addresses")
@Entity
public class Address extends BaseEntity {

  @Column(name = "country")
  private String country;

  @Column(name = "city")
  private String city;

  @Column(name = "street_name")
  private String streetName;

  @Column(name = "house_number")
  private String houseNumber;

  @Column(name = "apartment_number")
  private String apartmentNumber;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private User user;

  @Deprecated
  protected Address() {
    /* Required by Hibernate */
  }

  Address(final String country, final String city, final String streetName, final String houseNumber,
      final String apartmentNumber, final User user) {
    super(NonDefaultConstructorArgument.INSTANCE);

    this.country = country;
    this.city = city;
    this.streetName = streetName;
    this.houseNumber = houseNumber;
    this.apartmentNumber = apartmentNumber;
    this.user = user;
  }
}
