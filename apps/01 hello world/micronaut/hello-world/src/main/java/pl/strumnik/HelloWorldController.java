package pl.strumnik;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/")
public class HelloWorldController {

  @Get(produces = "text/plain")
  public String hello() {
    return "Hello world!";
  }
}
