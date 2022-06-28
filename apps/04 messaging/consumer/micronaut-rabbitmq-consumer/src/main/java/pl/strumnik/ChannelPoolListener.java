package pl.strumnik;

import com.rabbitmq.client.Channel;
import io.micronaut.rabbitmq.connect.ChannelInitializer;
import jakarta.inject.Singleton;
import java.io.IOException;

@Singleton
public class ChannelPoolListener extends ChannelInitializer {

  @Override
  public void initialize(final Channel channel, final String name) throws IOException {
    channel.exchangeDeclare("numbers", "direct", true);
    channel.queueDeclare("numbers-micronaut", true, false, false, null);
    channel.queueBind("numbers-micronaut", "numbers", "numbers-routing-key", null);

    channel.exchangeDeclare("objects", "direct", true);
    channel.queueDeclare("objects-micronaut", true, false, false, null);
    channel.queueBind("objects-micronaut", "objects", "objects-routing-key", null);
  }

}
