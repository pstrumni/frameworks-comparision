package pl.strumnik;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

  @RabbitListener(queues = "numbers-springboot")
  public void process(final String message) {
    if (message == null)
      throw new IllegalArgumentException("message is null");

    if (!message.equals("1"))
      throw new IllegalArgumentException("wrong message");
  }

  @RabbitListener(queues = "objects-springboot")
  public void process(final Payload payload) {
    if (payload == null)
      throw new IllegalArgumentException("payload is null");

    if (!payload.getName().equals("name") && !payload.getValue().equals(1L))
      throw new IllegalArgumentException("wrong payload");
  }
}
