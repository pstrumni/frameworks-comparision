package pl.strumnik;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@ApplicationScoped
public class ObjectsPublisher {

  @OnOverflow(OnOverflow.Strategy.NONE)
  @Channel("objects-out")
  Emitter<Payload> objectsEmitter;

  public void publish(final Payload payload) {
    objectsEmitter.send(payload);
  }
}
