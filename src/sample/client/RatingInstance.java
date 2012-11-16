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
  String id;

  @Persistent
  Date timestamp;

  @Persistent
  String rated;

  @Persistent
  String category;

  @Persistent
  float score;
}
