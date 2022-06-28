package pl.strumnik.web;

import io.quarkus.security.Authenticated;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Authenticated
@Path("/test")
public class SimpleRestController {

  @Path("/1")
  @GET
  public void test1() {
  }
}
