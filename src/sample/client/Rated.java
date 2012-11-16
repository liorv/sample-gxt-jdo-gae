package sample.client;

import java.util.List;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Rated extends BaseDataObject
{
  protected Rated(String id) {
    super(id);
  }

  public void addReward(Reward r, int count) {
    for (RewardCase rc : rewardCases) {
      if (rc.getId().equals(r.getId())) {
        rc.setCount(count + rc.getCount());
      }
    }
  }  

  @Persistent
  protected List<RewardCase> rewardCases;

  @Persistent
  protected List<StatRelationCategoryRated> stats;
}
