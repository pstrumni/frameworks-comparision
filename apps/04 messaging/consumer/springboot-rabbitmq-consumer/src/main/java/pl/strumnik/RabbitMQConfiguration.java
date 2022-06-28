package pl.strumnik;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

  @Bean
  public DirectExchange numbersExchange() {
    return ExchangeBuilder.directExchange("numbers").build();
  }

  @Bean
  public Queue numbersQueue() {
    return QueueBuilder.durable("numbers-springboot").build();
  }

  @Bean
  public Binding numbersBinding() {
    return BindingBuilder
        .bind(numbersQueue())
        .to(numbersExchange())
        .with("numbers-routing-key");
  }

  @Bean
  public DirectExchange objectsExchange() {
    return ExchangeBuilder.directExchange("objects").build();
  }

  @Bean
  public Queue objectsQueue() {
    return QueueBuilder.durable("objects-springboot").build();
  }

  @Bean
  public Binding objectsBinding() {
    return BindingBuilder
        .bind(objectsQueue())
        .to(objectsExchange())
        .with("objects-routing-key");
  }

  @Bean
  public Jackson2JsonMessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
