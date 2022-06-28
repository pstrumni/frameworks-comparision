package pl.strumnik;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

  private final NumbersPublisher numbersPublisher;
  private final ObjectsPublisher objectsPublisher;

  public TestRestController(final NumbersPublisher numbersPublisher, final ObjectsPublisher objectsClient) {
    this.numbersPublisher = numbersPublisher;
    this.objectsPublisher = objectsClient;
  }

  @GetMapping("/test1")
  public ResponseEntity<?> test1() {
    numbersPublisher.publish(1);

    return ResponseEntity.accepted().build();
  }

  @GetMapping("/test2")
  public ResponseEntity<?> test2() {
    final Payload payload = new Payload("name", 1L);

    objectsPublisher.publish(payload);

    return ResponseEntity.accepted().build();
  }
}
