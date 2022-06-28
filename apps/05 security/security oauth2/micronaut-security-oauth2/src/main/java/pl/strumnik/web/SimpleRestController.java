package pl.strumnik.web;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/test")
public class SimpleRestController {

  @Secured("role1")
  @Get("/1")
  public void test1() {
  }
}
