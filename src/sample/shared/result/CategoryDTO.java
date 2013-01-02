package sample.shared.result;

import java.io.Serializable;

import sample.shared.Response;

@SuppressWarnings("serial")
public class CategoryDTO extends NamedDTO implements Serializable, Response
{
  public CategoryDTO() {}

  public CategoryDTO(String name) {
    setName(name);
  }
}
