package jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class StatRelation extends DataObject
{
  protected StatRelation(Category c, String name, Class<?> clz)
  {
    super(StatRelation.class, "stat("+c.getName()+","+name+")");
    this.category = c;
    this.name = name;
    reset();
  }

  @Persistent
  private float min;

  @Persistent
  private float max;

  @Persistent
  private float mean;

  @Persistent
  private int count;

  @Persistent
  @Unowned
  private Category category;

  @Persistent
  private String name;

  public void updateStats(float score) {
    if (score < min) min = score;
    if (score > max) max = score;
    mean = (mean * count + score) / (count + 1);
    count++;
  }

  public float getMin() {
    return min;
  }

  public void setMin(float min) {
    this.min = min;
  }

  public float getMax() {
    return max;
  }

  public void setMax(float max) {
    this.max = max;
  }

  public float getMean() {
    return mean;
  }

  public void setMean(float mean) {
    this.mean = mean;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void reset() {
    max = Integer.MIN_VALUE;
    min = Integer.MAX_VALUE;
    mean = 0;
    count = 0;
  }
}
