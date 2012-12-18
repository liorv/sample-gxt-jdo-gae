package sample.client.dto;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
public class GroupDTO extends NamedDTO implements Serializable
{
  public GroupDTO() {
  }
  
  public GroupDTO(String name, Set<RatedDTO> members, Set<CategoryDTO> categories) {
    setName(name);
    setMembers(members);
    setCategories(categories);
  }
  
  private Set<RatedDTO> members;
  
  private Set<CategoryDTO> categories;

  public Set<RatedDTO> getMembers() {
    return members;
  }

  public void setMembers(Set<RatedDTO> members) {
    this.members = members;
  }

  public Set<CategoryDTO> getCategories() {
    return categories;
  }

  public void setCategories(Set<CategoryDTO> categories) {
    this.categories = categories;
  }
}
