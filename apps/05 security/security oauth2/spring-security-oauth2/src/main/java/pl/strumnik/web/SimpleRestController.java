package pl.strumnik.web;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class SimpleRestController {

  @Secured("role1")
  @GetMapping("/1")
  public void test1() {
  }
}
