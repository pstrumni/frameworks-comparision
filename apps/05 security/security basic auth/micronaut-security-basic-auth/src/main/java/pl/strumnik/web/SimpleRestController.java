package pl.strumnik.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/test")
public class SimpleRestController {

  @Get("/1")
  public void test1() {
  }
}
