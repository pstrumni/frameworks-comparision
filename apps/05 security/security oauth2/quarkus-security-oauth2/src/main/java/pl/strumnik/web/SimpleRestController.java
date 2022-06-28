package pl.strumnik.web;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/test")
public class SimpleRestController {

  @RolesAllowed("role1")
  @Path("/1")
  @GET
  public void test1() {
  }
}
