package sample.client.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RatedDTO implements Serializable
{
  public RatedDTO() {}

  public RatedDTO(String name) {
    this.name = name;
  }

  private String name;
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
