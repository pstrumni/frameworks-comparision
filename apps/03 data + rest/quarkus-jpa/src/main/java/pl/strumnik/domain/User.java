package pl.strumnik.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "users")
@Entity
public class User extends BaseEntity {

  @Column(name = "login")
  private String login;

  @Column(name = "password")
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "users_authorities",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "authority_id")
  )
  private Set<Authority> authorities = new HashSet<>();

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
  private Set<Address> addresses = new HashSet<>();

  @Deprecated
  protected User() {
    /* Required by Hibernate */
  }

  public User(final String login, final String password, final String email, final String firstName,
      final String lastName, final Set<Authority> authorities) {
    super(NonDefaultConstructorArgument.INSTANCE);

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
