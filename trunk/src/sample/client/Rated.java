package sample.client;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Rated extends BaseDataObject
{
  protected Rated(String id) {
    super(Rated.class, id); 
    rewardCases = new HashSet<RewardCase>();
    stats = new HashSet<StatRelationCategoryRated>();
  }

  public void addReward(Reward r, int count) {
    for (RewardCase rc : rewardCases) {
      if (rc.reward.equals(r)) {
        rc.count = count + rc.count;
      }
    }
  }

  public void updateStats(Category category, float score) {
    for (StatRelationCategoryRated stat : stats) {
      if (stat.category.equals(category.name)) {
        stat.updateStats(score);
      }
    }
  }

  public StatRelationCategoryRated getStat(Category c) {
    for (StatRelationCategoryRated stat : stats) {
      if (stat.category.equals(c.name)) { return stat; }
    }

    StatRelationCategoryRated newStat =
        new StatRelationCategoryRated(c, this, c.isPercent);
    stats.add(newStat);
    return newStat;
  }

  @Persistent
  @Element(dependent="true")
  public Set<RewardCase> rewardCases;

  @Persistent
  @Element(dependent="true")
  public Set<StatRelationCategoryRated> stats;
}
