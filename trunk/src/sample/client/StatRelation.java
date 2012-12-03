package sample.client;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType=IdentityType.DATASTORE)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class StatRelation
{
  protected StatRelation(boolean isPercent) {
    this.isPercent = isPercent;
    reset();
  }
  
  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  public Key key;

  @Persistent
  public boolean isPercent;

  @Persistent
  public float min;

  @Persistent
  public float max;

  @Persistent
  public float mean;

  @Persistent
  public int count;

  public void updateStats(float score) {
    if (score < min) min = score;
    if (score > max) max = score;
    mean = (mean * count + score) / (count + 1);
    count++;
  }

  public void reset() {
    max = Integer.MIN_VALUE;
    min = Integer.MAX_VALUE;
    mean = 0;
    count = 0;
  }
}
