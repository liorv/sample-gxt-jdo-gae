
package jdo;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Reward extends BaseDataObject
{
  protected Reward(String id) {
    super(Reward.class, id);
  }
}
