
package sample.client;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import jdo.DataObject;

@PersistenceCapable
public class RewardCase extends DataObject
{
  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  protected RewardCase(String id) {
    super(id);
  }
  
  @Persistent
  protected int count;
}
