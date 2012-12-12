package jdo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Grouping extends BaseDataObject
{
  @Persistent
  @Unowned
  @Element(dependent = "false")
  protected Set<Rated> members;

  @Persistent
  @Element(dependent = "true")
  protected HashSet<StatRelation> stats;

  @Persistent
  @Unowned
  @Element(dependent = "false")
  protected Set<Category> categories;

  protected Grouping(String id) {
    super(Grouping.class, id);
    members = new HashSet<Rated>();
    stats = new HashSet<StatRelation>();
  }

  public StatRelation getStat(Category c) {
    for (StatRelation stat : stats) {
      if (stat.getCategory().equals(c)) { return stat; }
    }
    StatRelation newStat = new StatRelation(c, getName(), Grouping.class);
    stats.add(newStat);
    return newStat;
  }

  public void updateStats(Category category, float score) {
    for (StatRelation stat : stats) {
      if (stat.getCategory().equals(category)) {
        stat.updateStats(score);
      }
    }
  }

  public void attachCategory(Category c) {
    if (categories.contains(c)) return;

    categories.add(c);
    c.addGroup(this);

    c.addGroupStat(getStat(c));

    for (Rated r : members) {
      c.addRatedStat(r.getStat(c));
    }
  }

  public void addMember(Rated r) {
    if (members.contains(r)) return;

    members.add(r);
    for (Category c : categories) {
      c.addRatedStat(r.getStat(c));
    }
  }

  public Iterable<StatRelation> getStats() {
    return stats;
  }
}
