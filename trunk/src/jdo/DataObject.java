package jdo;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
abstract public class DataObject
{ 
  public static Key AppKey = KeyFactory.createKey("Application", "ir8");
  
  @PrimaryKey
  @Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
  @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
  protected String encodedKey;
  
  @Persistent
  @Extension(vendorName="datanucleus", key="gae.pk-name", value="true")
  protected String id;
  
  @Persistent
  @Extension(vendorName="datanucleus", key="gae.parent-pk", value="true")
  protected String parentKey;
  
  protected DataObject(String id) {
    this.id = id;
    parentKey = KeyFactory.keyToString(AppKey);
  }
  
  protected DataObject(String id, String kind, String parentId) {
    this.id = id;
    Key parent_pk = KeyFactory.createKey(kind, parentId);
    parentKey = KeyFactory.keyToString(parent_pk);
  }

  public String getId() {
    return id;
  }
  
  public String getParent() {
    return parentKey;
  }
  
  @Override
  public String toString() {
    return getClass().getSimpleName() + "::" +getId(); 
  }
}
