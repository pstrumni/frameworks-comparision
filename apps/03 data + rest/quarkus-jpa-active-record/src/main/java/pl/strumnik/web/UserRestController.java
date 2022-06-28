package pl.strumnik.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import pl.strumnik.domain.ApplicationException;
import pl.strumnik.domain.Authority;
import pl.strumnik.domain.User;

@Path("/user")
public class UserRestController {

  @POST
  @Transactional
  public Response createUser(final CreateUserRequest request) {

    final Set<Authority> authorities;
    if (request.authorities != null && !request.authorities.isEmpty()) {
      authorities = request.authorities.stream()
          .map(authorityDto -> Authority.<Authority>findByIdOptional(authorityDto.id)
              .orElseThrow(ApplicationException::new))
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

    user.persist();

    return Response.status(Status.CREATED).entity(user).build();
  }

  @GET
  @Transactional
  public Response getUsers() {
    return Response.ok(User.findAll().<User>list()).build();
  }

  @Path("/{id}")
  @GET
  @Transactional
  public Response getUser(@PathParam("id") final String id) {
    final User user = User.findByIdWithAddressesAndAuthorities(id).orElseThrow(ApplicationException::new);
    return Response.ok(user).build();
  }

  @Path("/{id}")
  @DELETE
  @Transactional
  public Response deleteUser(@PathParam("id") final String id) {
    User.deleteById(id);
    return Response.noContent().build();
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
