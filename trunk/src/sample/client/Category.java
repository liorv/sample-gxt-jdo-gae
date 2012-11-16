package sample.client;

import java.util.Iterator;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Category extends BaseDataObject
{
  protected Category(String category, boolean isPercent) {
    super(category);
    this.isPercent = isPercent;
  }
  
  public void rate(Rated r, float score) {
    updateRatedStat(r, score);
    
    for (Iterator<Grouping> it = groups.iterator(); it.hasNext();) {
      Grouping g = (Grouping) it.next();
      if(g.members().contains(r)) {
        updateGroupStat(g, score);
      }
    }
  }
  
  private void updateGroupStat(Grouping g, float score) {
    for (StatRelationCategoryGroup stat : groupStats) {
      if(stat.group.equals(g)) {
        stat.updateStats(score);
      }
    }
  }

  private void updateRatedStat(Rated r, float score) {
    for (StatRelationCategoryRated stat : ratedStats) {
      if(stat.rated.equals(r)) {
        stat.updateStats(score);
      }
    }
  }

  @Persistent
  protected boolean isPercent;
 
  @Persistent
  protected Set<Grouping> groups;
  
  @Persistent
  protected Set<StatRelationCategoryRated> ratedStats;
  
  @Persistent
  protected Set<StatRelationCategoryGroup> groupStats;
 
}
