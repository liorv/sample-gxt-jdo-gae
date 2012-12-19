package jdo;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Rated extends BaseDataObject
{
  protected Rated(String id) {
    super(Rated.class, id);
    rewardCases = new HashSet<RewardCase>();
    stats = new HashSet<StatRelation>();
  }

  public void addReward(Reward r, int count) {
    for (RewardCase rc : rewardCases) {
      if (rc.reward.equals(r)) {
        rc.count = count + rc.count;
      }
    }
  }

  public void updateStats(Category category, float score) {
    for (StatRelation stat : stats) {
      if (stat.getCategory().equals(category)) {
        stat.updateStats(score);
      }
    }
  }

  public StatRelation getStat(Category c) {
    for (StatRelation stat : stats) {
      if (stat.getCategory().equals(c)) { return stat; }
    }

    StatRelation newStat = new StatRelation(c, getName(), Rated.class);
    stats.add(newStat);
    return newStat;
  }

  public Set<RewardCase> getRewardCases() {
    return rewardCases;
  }

  public void setRewardCases(Set<RewardCase> rewardCases) {
    this.rewardCases = rewardCases;
  }

  public Set<StatRelation> getStats() {
    return stats;
  }

  public void setStats(Set<StatRelation> stats) {
    this.stats = stats;
  }

  @Persistent
  @Element(dependent = "true")
  protected Set<RewardCase> rewardCases;

  @Persistent
  @Unowned
  @Element(dependent = "false")
  protected Set<StatRelation> stats;
}
