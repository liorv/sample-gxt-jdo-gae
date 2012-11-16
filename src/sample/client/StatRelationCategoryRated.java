package sample.client;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
abstract public class StatRelationCategoryRated extends StatRelation
{
  protected StatRelationCategoryRated(Category c, Rated r, boolean isPercent) {
    super(c.getId(), r.getId(), isPercent);
    category = c;
    rated = r;
  }
  
  @Persistent
  protected Category category;
  
  @Persistent
  protected Rated rated;
}
