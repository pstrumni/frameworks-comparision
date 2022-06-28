package pl.strumnik;

public class Payload {

  private String name;
  private Long value;

  public Payload(final String name, final Long value) {
    this.name = name;
    this.value = value;
  }

  public Payload() {
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(final Long value) {
    this.value = value;
  }
}
