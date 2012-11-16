
package sample.client;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Reward extends BaseDataObject
{
  protected Reward(String id) {
    super(id);
  }
}
