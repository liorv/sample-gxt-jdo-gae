package sample.client;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class StatRelationCategoryRated extends StatRelation
{
  protected StatRelationCategoryRated(Category c, Rated r, boolean isPercent) {
    super(isPercent);
    category = c.name;
    rated = r.name;
  }

  @Persistent
  public String category;

  @Persistent
  public String rated;
}
