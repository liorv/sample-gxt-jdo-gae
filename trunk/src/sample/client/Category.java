package sample.client;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Category extends BaseDataObject
{
  protected Category(String id, boolean isPercent) {
    super(null, Category.class.getSimpleName(), id);
    this.isPercent = isPercent;
    groups = new HashSet<Grouping>();
  }

  public void rate(Rated rated, float score) {
    for (Iterator<Grouping> it = groups.iterator(); it.hasNext();) {
      Grouping g = it.next();
      if (g.members.contains(rated)) {
        g.updateStats(this, score);
        rated.updateStats(this, score);
      }
    }
  }

  public void addGroup(Grouping g) {
    groups.add(g);
    g.getStat(this);
    for (Rated r : g.members) {
      r.getStat(this);
    }
  }

  @Persistent
  public boolean isPercent;

  @Persistent
  @Unowned
  public Set<Grouping> groups;
}
