package pl.strumnik.domain;

public class Authority extends BaseDomainObject {

  private String name;
  private String description;

  public Authority(final String id, final String name, final String description) {
    super(id);
    this.name = name;
    this.description = description;
  }
}
