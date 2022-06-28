package pl.strumnik;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class NumbersPublisher {

  private final RabbitTemplate rabbitTemplate;

  public NumbersPublisher(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void publish(final int i) {
    rabbitTemplate.convertAndSend("numbers", "numbers-routing-key", String.valueOf(i));
  }
}
