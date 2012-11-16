package sample.client;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import jdo.DataObject;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class BaseDataObject extends DataObject
{
  protected BaseDataObject(String id) {
    super(id);
  }

  @Persistent
  protected String description;

  @Persistent
  protected String icon;
}
