package sample.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Grouping extends BaseDataObject
{
  protected Grouping(String id) {
    super(null, Grouping.class.getSimpleName(), id);
    members = new HashSet<Rated>();
  }

  @Persistent
  @Unowned
  public Set<Rated> members;

  @Persistent
  @Element(dependent="true")
  public List<StatRelationCategoryGroup> stats;

  public StatRelationCategoryGroup getStat(Category c) {
    for (StatRelationCategoryGroup stat : stats) {
      if (stat.category.equals(c.name)) { return stat; }
    }
    StatRelationCategoryGroup newStat =
        new StatRelationCategoryGroup(c, this, c.isPercent);
    stats.add(newStat);
    return newStat;
  }

  public void updateStats(Category category, float score) {
    for (StatRelationCategoryGroup stat : stats) {
      if (stat.category.equals(category.name)) {
        stat.updateStats(score);
      }
    }
  }

  public void addMember(Rated r) {
    members.add(r);
  }
}
