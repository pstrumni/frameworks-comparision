package pl.strumnik;

import io.vertx.core.json.JsonObject;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class RabbitMQConsumer {

  @Incoming("numbers-in")
  public void process(final String message) {
    if (message == null)
      throw new IllegalArgumentException("message is null");

    if (!message.equals("1"))
      throw new IllegalArgumentException("wrong message");
  }

  @Incoming("objects-in")
  public void process(final JsonObject jsonObject) {
    if (jsonObject == null)
      throw new IllegalArgumentException("jsonObject is null");

    final Payload payload = jsonObject.mapTo(Payload.class);

    if (payload == null)
      throw new IllegalArgumentException("payload is null");

    if (!payload.getName().equals("name") && !payload.getValue().equals(1L))
      throw new IllegalArgumentException("wrong payload");
  }

}
