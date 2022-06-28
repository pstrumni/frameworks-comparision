package pl.strumnik.web;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.Introspected.AccessKind;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import pl.strumnik.domain.ApplicationException;
import pl.strumnik.domain.Authority;
import pl.strumnik.domain.User;
import pl.strumnik.repository.AuthorityRepository;
import pl.strumnik.repository.UserRepository;

@Controller("/user")
public class UserRestController {

  private final UserRepository userRepository;
  private final AuthorityRepository authorityRepository;

  public UserRestController(final UserRepository userRepository, final AuthorityRepository authorityRepository) {
    this.userRepository = userRepository;
    this.authorityRepository = authorityRepository;
  }

  @Post
  public HttpResponse<User> createUser(@Body final CreateUserRequest request) {

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

    return HttpResponse.status(HttpStatus.CREATED).body(user);
  }

  @Get
  public HttpResponse<Set<User>> getUsers() {
    return HttpResponse.ok(userRepository.findAll());
  }

  @Get("/{id}")
  public HttpResponse<User> getUser(@PathVariable("id") final String id) {
    return HttpResponse.ok(userRepository.findById(id).orElseThrow(ApplicationException::new));
  }

  @Delete("/{id}")
  public HttpResponse<?> deleteUser(@PathVariable("id") final String id) {
    userRepository.delete(id);
    return HttpResponse.noContent();
  }

  @Introspected(accessKind = AccessKind.FIELD)
  public static final class CreateUserRequest {

    public String login;
    public String password;
    public String email;
    public String firstName;
    public String lastName;
    public List<AuthorityDto> authorities;
    public List<AddressDto> addresses;
  }

  @Introspected(accessKind = AccessKind.FIELD)
  public static final class AuthorityDto {

    public String id;
  }

  @Introspected(accessKind = AccessKind.FIELD)
  public static final class AddressDto {

    public String country;
    public String city;
    public String streetName;
    public String houseNumber;
    public String apartmentNumber;
  }
}
