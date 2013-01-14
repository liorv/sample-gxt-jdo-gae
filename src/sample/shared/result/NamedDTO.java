package sample.shared.result;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
abstract public class NamedDTO implements Serializable, Result
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