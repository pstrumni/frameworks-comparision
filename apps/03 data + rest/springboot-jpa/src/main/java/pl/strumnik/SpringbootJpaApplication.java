package pl.strumnik;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import javax.persistence.NamedEntityGraph;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.nativex.hint.TypeHint;

@TypeHint(types = NamedEntityGraph.class)
@SpringBootApplication
public class SpringbootJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbootJpaApplication.class, args);
  }

  @Bean
  protected Module hibernate5Module() {
    return new Hibernate5Module();
  }
}
