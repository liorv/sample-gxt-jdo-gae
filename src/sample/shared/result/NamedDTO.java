package sample.shared.result;

import java.io.Serializable;

import sample.shared.Response;

@SuppressWarnings("serial")
abstract public class NamedDTO implements Serializable, Response
{
  protected String name;

  protected NamedDTO() {}

  protected NamedDTO(String name) {
    setName(name);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}