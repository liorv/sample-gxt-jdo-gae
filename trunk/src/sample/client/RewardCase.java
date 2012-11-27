package sample.client;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class RewardCase
{
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) 
  public Key key;
  
  @Persistent
  public Reward reward;

  @Persistent
  public int count;
}
