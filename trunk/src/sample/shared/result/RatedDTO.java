package sample.shared.result;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class RatedDTO extends NamedDTO implements Serializable, Result
{
  public RatedDTO() {}

  public RatedDTO(String name) {
    setName(name);
  }
}
