package sample.client;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Grouping extends BaseDataObject
{
  protected Grouping(String id) {
    super(id);
    members = new HashSet<Rated>();
  }

  public Set<Rated> members() {
    return members;
  }

  @Persistent
  protected Set<Rated> members;
}
