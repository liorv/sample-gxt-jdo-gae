package jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class DataObject
{ 
  @PrimaryKey
  @Persistent
  public Key key;

  @Persistent
  public String name;

  protected DataObject(Key group, String kind, String id) {
    this.name = id;
    key = KeyFactory.createKey(group, kind, id);
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
