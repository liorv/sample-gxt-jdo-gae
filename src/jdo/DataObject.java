package jdo;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import jdo.key.KFProvider;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)

abstract public class DataObject
{ 
  public String getPk() {
    return pk;
  }

  public void setPk(String pk) {
    this.pk = pk;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @PrimaryKey
  @Persistent
  protected String pk;

  @Persistent
  protected String name;

  protected <T> DataObject(Class<T> clz, String id) {
    this.name = id;
    pk = KFProvider.key(clz, id);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof DataObject) {
      DataObject o = (DataObject) obj;
      String itsClass = o.getClass().getSimpleName();
      String myClass = getClass().getSimpleName();
      return (itsClass.equals(myClass) && o.pk.equals(pk));
    }
    return false;
  };
  
  @Override
  public int hashCode() {
    return pk.hashCode();
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() + "::" + name;
  }
}
