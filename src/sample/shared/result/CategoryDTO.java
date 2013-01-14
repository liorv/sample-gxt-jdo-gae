package sample.shared.result;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class CategoryDTO extends NamedDTO implements Serializable, Result
{
  public CategoryDTO() {}

  public CategoryDTO(String name) {
    setName(name);
  }
}
