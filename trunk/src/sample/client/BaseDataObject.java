package sample.client;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

import jdo.DataObject;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class BaseDataObject extends DataObject
{
  protected BaseDataObject(Key group, String kind, String id) {
    super(group, kind, id);
  }

  @Persistent
  public String description;

  @Persistent
  public String icon;
}
