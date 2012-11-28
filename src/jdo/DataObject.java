package jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import jdo.key.KeyFactoryProvider;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class DataObject
{ 
  @PrimaryKey
  @Persistent
  public Object key;

  @Persistent
  public String name;

  protected DataObject(String kind, String id) {
    this.name = id;
    key = KeyFactoryProvider.getKeyfactory().createKey(kind, id);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DataObject) {
      DataObject o = (DataObject) obj;
      String itsClass = o.getClass().getSimpleName();
      String myClass = getClass().getSimpleName();
      return (itsClass.equals(myClass) && o.name.equals(name));
    }
    return false;
  };

  @Override
  public String toString() {
    return getClass().getSimpleName() + "::" + name;
  }
}
