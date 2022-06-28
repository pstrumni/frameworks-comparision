package pl.strumnik;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class TestRestController {

  private final NumbersPublisher numbersPublisher;
  private final ObjectsPublisher objectsPublisher;

  public TestRestController(final NumbersPublisher numbersPublisher, final ObjectsPublisher objectsPublisher) {
    this.numbersPublisher = numbersPublisher;
    this.objectsPublisher = objectsPublisher;
  }

  @Path("/test1")
  @GET
  public Response test1() {
    numbersPublisher.publish(1);

    return Response.accepted().build();
  }

  @Path("/test2")
  @GET
  public Response test2() {
    final Payload payload = new Payload("name", 1L);

    objectsPublisher.publish(payload);

    return Response.accepted().build();
  }
}
