package sample.client.dto;

import java.io.Serializable;
import java.util.Set;

@SuppressWarnings("serial")
public class GroupDTO implements Serializable
{
  public GroupDTO() {}

  public GroupDTO(String name, Set<String> members, Set<String> categories) {
    this.name = name;
    this.members = members;
    this.categories = categories;
  }

  private String name;
  
  private Set<String> members;
  
  private Set<String> categories;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Set<String> getMembers() {
    return members;
  }

  public void setMembers(Set<String> members) {
    this.members = members;
  }

  public Set<String> getCategories() {
    return categories;
  }

  public void setCategories(Set<String> categories) {
    this.categories = categories;
  }
}
