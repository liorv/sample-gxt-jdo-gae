package sample.client.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CategoryDTO extends NamedDTO implements Serializable
{
  public CategoryDTO() {}

  public CategoryDTO(String name) {
    setName(name);
  }
}
