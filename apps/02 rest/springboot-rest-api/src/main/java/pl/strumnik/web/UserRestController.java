package pl.strumnik.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strumnik.domain.ApplicationException;
import pl.strumnik.domain.Authority;
import pl.strumnik.domain.User;
import pl.strumnik.repository.AuthorityRepository;
import pl.strumnik.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserRestController {

  private final UserRepository userRepository;
  private final AuthorityRepository authorityRepository;

  public UserRestController(final UserRepository userRepository, final AuthorityRepository authorityRepository) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody final CreateUserRequest request) {

    final Set<Authority> authorities;
    if (request.authorities != null && !request.authorities.isEmpty()) {
      authorities = request.authorities.stream()
          .map(authorityDto -> authorityRepository.findById(authorityDto.id).orElseThrow(ApplicationException::new))
          .collect(Collectors.toSet());
    } else {
      authorities = new HashSet<>();
    }

    final User user = new User(request.login, request.password, request.email, request.firstName, request.lastName,
        authorities);

    if (request.addresses != null && !request.addresses.isEmpty()) {
      for (final AddressDto addressDto : request.addresses) {
        user.addAddress(addressDto.country, addressDto.city, addressDto.streetName, addressDto.houseNumber,
            addressDto.apartmentNumber);
      }
    }

    userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }

  @GetMapping
  public ResponseEntity<Set<User>> getUsers() {
    return ResponseEntity.ok(userRepository.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") final String id) {
    return ResponseEntity.ok(userRepository.findById(id).orElseThrow(ApplicationException::new));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable("id") final String id) {
    userRepository.delete(id);
    return ResponseEntity.noContent().build();
  }

  public static final class CreateUserRequest {

    public String login;
    public String password;
    public String email;
    public String firstName;
    public String lastName;
    public List<AuthorityDto> authorities;
    public List<AddressDto> addresses;
  }

  public static final class UpdateUserRequest {

    public String login;
    public String password;
    public String email;
    public String firstName;
    public String lastName;
    public List<AuthorityDto> authorities;
    public List<AddressDto> addresses;
  }

  public static final class AuthorityDto {

    public String id;
  }

  public static final class AddressDto {

    public String country;
    public String city;
    public String streetName;
    public String houseNumber;
    public String apartmentNumber;
  }
}
