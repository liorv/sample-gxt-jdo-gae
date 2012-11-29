package sample.client;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class RewardCase
{
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE) 
  public String key;
  
  @Persistent
  public Reward reward;

  @Persistent
  public int count;
}
