package pl.strumnik;

import jakarta.inject.Singleton;

@Singleton
public class ObjectsPublisher {

  private final ObjectsClient objectsClient;

  public ObjectsPublisher(final ObjectsClient objectsClient) {
    this.objectsClient = objectsClient;
  }

  public void publish(final Payload payload) {
    objectsClient.send(payload);
  }
}
