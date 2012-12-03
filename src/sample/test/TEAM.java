package sample.test;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import sample.client.BaseDataObject;


import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class TEAM extends BaseDataObject
{
  public TEAM(String id) {
    super(TEAM.class, id);
  }

  @Persistent
  @Unowned
  @Element(dependent="false")
  public Set<PERSON> members = new HashSet<PERSON>();

  public void addMember(PERSON r) {
    //if(members == null) members = new HashSet<PERSON>();
    members.add(r);
  }
}
