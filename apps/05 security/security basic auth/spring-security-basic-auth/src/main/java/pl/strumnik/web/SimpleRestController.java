package pl.strumnik.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class SimpleRestController {

  @GetMapping("/1")
  public void test1() {
  }

}
