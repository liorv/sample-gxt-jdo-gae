package sample.client;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
abstract public class StatRelationCategoryGroup extends StatRelation
{
  protected StatRelationCategoryGroup(Category c, Grouping g, boolean isPercent) {
    super(c.getId(), g.getId(), isPercent);
    category = c;
    group = g;
  }
  
  @Persistent
  protected Category category;
  
  @Persistent
  protected Grouping group;
}
