package sample.client;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import jdo.DataObject;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class StatRelation extends DataObject
{
  public static String getKey(String typeA, String typeB) {
    return typeA + "<->" + typeB;
  }

  protected StatRelation(String typeA, String typeB, boolean isPercent) {
    super(getKey(typeA, typeB));
    this.isPercent = isPercent;
  }
  
  @Persistent
  protected boolean isPercent;

  @Persistent
  protected float min;

  @Persistent
  protected float max;

  @Persistent
  protected float mean;

  @Persistent
  protected int count;

  public boolean isPercent() {
    return isPercent;
  }
  
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
