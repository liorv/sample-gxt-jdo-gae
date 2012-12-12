package jdo;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Category extends BaseDataObject
{
  @Persistent
  protected int max;

  @Persistent
  @Unowned
  protected Set<Grouping> groups;

  @Persistent
  @Unowned
  protected Set<StatRelation> ratedStats;

  @Persistent
  @Unowned
  protected Set<StatRelation> groupStats;

  protected Category(String id, int max) {
    super(Category.class, id);
    this.max = max;
    groups = new HashSet<Grouping>();
    groupStats = new HashSet<StatRelation>();
    ratedStats = new HashSet<StatRelation>();
  }

  public int getMax() {
    return max;
  }

  public void setMax(int max) {
    this.max = max;
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
  }

  public void addRatedStat(StatRelation stat) {
    ratedStats.add(stat);
  }

  public void addGroupStat(StatRelation stat) {
    groupStats.add(stat);
  }

  public Iterable<StatRelation> getGroupStats() {
    return groupStats;
  }

  public Iterable<StatRelation> getRatedStats() {
    return ratedStats;
  }

}
