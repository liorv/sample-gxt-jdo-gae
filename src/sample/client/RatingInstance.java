package sample.client;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class RatingInstance
{
  public RatingInstance(String rated, String category, float score) {
    timestamp = new Date(System.currentTimeMillis());
    this.rated = rated;
    this.category = category;
    this.score = score;
  }
  
  @Persistent(valueStrategy = IdGeneratorStrategy.UUIDSTRING)
  public String id;

  @Persistent
  public Date timestamp;

  @Persistent
  public String rated;

  @Persistent
  public String category;

  @Persistent
  public float score;
}
