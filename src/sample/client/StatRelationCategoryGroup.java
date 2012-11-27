package sample.client;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;


@PersistenceCapable
public class StatRelationCategoryGroup extends StatRelation
{
  protected StatRelationCategoryGroup(Category c, Grouping g, boolean isPercent)
  {
    super(isPercent);
    category = c.name;
    group = g.name;
  }

  @Persistent
  public String category;

  @Persistent
  public String group;
}
