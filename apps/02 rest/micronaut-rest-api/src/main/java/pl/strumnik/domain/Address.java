package pl.strumnik.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.core.annotation.ReflectiveAccess;

@ReflectiveAccess
public class Address extends BaseDomainObject {

  private String country;
  private String city;
  private String streetName;
  private String houseNumber;
  private String apartmentNumber;

  @JsonIgnore
  private User user;

  Address(final String country, final String city, final String streetName, final String houseNumber,
      final String apartmentNumber, final User user) {
    this.country = country;
    this.city = city;
    this.streetName = streetName;
    this.houseNumber = houseNumber;
    this.apartmentNumber = apartmentNumber;
    this.user = user;
  }
}
