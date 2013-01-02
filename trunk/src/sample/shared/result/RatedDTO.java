package sample.shared.result;

import java.io.Serializable;

import sample.shared.Response;

@SuppressWarnings("serial")
public class RatedDTO extends NamedDTO implements Serializable, Response
{
  public RatedDTO() {}

  public RatedDTO(String name) {
    setName(name);
  }
}
