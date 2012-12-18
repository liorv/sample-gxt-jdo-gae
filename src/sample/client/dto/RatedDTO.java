package sample.client.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RatedDTO extends NamedDTO implements Serializable
{
  public RatedDTO() {}

  public RatedDTO(String name) {
    setName(name);
  }
}
