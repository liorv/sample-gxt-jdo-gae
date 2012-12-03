package sample.client;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable(identityType=IdentityType.DATASTORE)
public class RewardCase
{ 
  @Persistent
  @PrimaryKey
  Key pk;
  
  @Persistent
  public Reward reward;

  @Persistent
  public int count;
}
