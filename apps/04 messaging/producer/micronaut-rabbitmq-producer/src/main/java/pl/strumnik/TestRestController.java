package pl.strumnik;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller
public class TestRestController {

  private final NumbersPublisher numbersPublisher;
  private final ObjectsPublisher objectsPublisher;

  public TestRestController(final NumbersPublisher numbersPublisher, final ObjectsPublisher objectsPublisher) {
    this.numbersPublisher = numbersPublisher;
    this.objectsPublisher = objectsPublisher;
  }

  @Get("/test1")
  public HttpResponse<?> test1() {
    numbersPublisher.publish(1);

    return HttpResponse.accepted();
  }

  @Get("/test2")
  public HttpResponse<?> test2() {
    final Payload payload = new Payload("name", 1L);

    objectsPublisher.publish(payload);

    return HttpResponse.accepted();
  }
}
