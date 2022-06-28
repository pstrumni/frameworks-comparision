package pl.strumnik;

import io.micronaut.rabbitmq.annotation.Binding;
import io.micronaut.rabbitmq.annotation.RabbitClient;

@RabbitClient("objects")
public interface ObjectsClient {

  @Binding("objects-routing-key")
  void send(final Payload value);
}
