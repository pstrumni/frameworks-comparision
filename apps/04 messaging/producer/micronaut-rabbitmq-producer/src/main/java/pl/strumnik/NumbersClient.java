package pl.strumnik;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

@RabbitClient("numbers")
public interface NumbersClient {

  @Binding("numbers-routing-key")
  void send(final String value);
}
