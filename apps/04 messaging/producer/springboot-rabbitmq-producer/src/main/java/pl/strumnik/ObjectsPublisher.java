package pl.strumnik;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ObjectsPublisher {

  private final RabbitTemplate rabbitTemplate;

  public ObjectsPublisher(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publish(final Payload payload) {
    rabbitTemplate.convertAndSend("objects", "objects-routing-key", payload);
  }

}
