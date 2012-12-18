package sample.client.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
abstract public class NamedDTO implements Serializable
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