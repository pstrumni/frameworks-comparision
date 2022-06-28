package pl.strumnik.domain;

import java.util.HashSet;
import java.util.Set;

public class User extends BaseDomainObject {

  private String login;
  private String password;
  private String email;
  private String firstName;
  private String lastName;
  private Set<Authority> authorities = new HashSet<>();
  private Set<Address> addresses = new HashSet<>();

  public User(final String login, final String password, final String email, final String firstName,
      final String lastName, final Set<Authority> authorities) {
    this.login = login;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.authorities.addAll(authorities);
  }

  public void addAddress(final String country, final String city, final String streetName, final String houseNumber,
      final String apartmentNumber) {
    final Address address = new Address(country, city, streetName, houseNumber, apartmentNumber, this);
    this.addresses.add(address);
  }
}
