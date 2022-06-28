package pl.strumnik;

import io.micronaut.rabbitmq.annotation.Queue;
import io.micronaut.rabbitmq.annotation.RabbitListener;

@RabbitListener
public class RabbitMQConsumer {

  @Queue("numbers-micronaut")
  public void process(final String message) {
    if (message == null)
      throw new IllegalArgumentException("message is null");

    if (!message.equals("1"))
      throw new IllegalArgumentException("wrong message");
  }

  @Queue("objects-micronaut")
  public void process(final Payload payload) {
    if (payload == null)
      throw new IllegalArgumentException("payload is null");

    if (!payload.getName().equals("name") && !payload.getValue().equals(1L))
      throw new IllegalArgumentException("wrong payload");
  }
}
