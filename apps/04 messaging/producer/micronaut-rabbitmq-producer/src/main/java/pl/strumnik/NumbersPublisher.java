package pl.strumnik;

import jakarta.inject.Singleton;

@Singleton
public class NumbersPublisher {

  private final NumbersClient numbersClient;

  public NumbersPublisher(final NumbersClient numbersClient) {
    this.numbersClient = numbersClient;
  }

  public void publish(final int i) {
    numbersClient.send(String.valueOf(i));
  }
}
