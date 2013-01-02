package sample.shared.result;

import java.io.Serializable;

import sample.shared.Response;

@SuppressWarnings("serial")
public class StatsDTO implements Serializable, Response
{
  public StatsDTO() {}

  public StatsDTO(String c, String n, float min, float max, float mean,
      int count)
  {
    this.category = c;
    this.name = n;
    this.min = min;
    this.max = max;
    this.mean = mean;
    this.count = count;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  private String category;
  private String name;
  private float min;
  private float max;
  private float mean;
  private int count;
}
