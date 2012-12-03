package sample.test;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;

import sample.client.BaseDataObject;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class PERSON extends BaseDataObject
{
  public PERSON(String id) {
    super(PERSON.class, id); 
  }
}
