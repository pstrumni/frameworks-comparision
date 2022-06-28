package pl.strumnik;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.OnOverflow;

@ApplicationScoped
public class NumbersPublisher {

  @OnOverflow(OnOverflow.Strategy.NONE)
  @Channel("numbers-out")
  Emitter<String> numbersEmitter;

  public void publish(final int i) {
    numbersEmitter.send(String.valueOf(i));
  }
}
