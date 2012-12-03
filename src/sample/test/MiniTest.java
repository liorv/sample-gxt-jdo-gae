package sample.test;

import javax.jdo.Extent;
import javax.jdo.annotations.PersistenceAware;


import jdo.JDOException;
import jdo.JDOSession;
import jdo.JDOUtils;

@PersistenceAware
public class MiniTest
{
  public static void run() {
    try {
      printState("START");
      JDOUtils.clear(TEAM.class);
      JDOUtils.clear(PERSON.class);
      printState("Clear All - DONE");

      PERSON p = new PERSON("Nadine");
      TEAM t = new TEAM("raptors");
      JDOUtils.persist(t);
      JDOUtils.persist(p);
      
      printState("Load - DONE");

      JDOSession session = JDOSession.open(true);
      t = session.find(TEAM.class, "raptors"); 
      p = session.find(PERSON.class, "Nadine"); 
      
      assert(t != null && p != null);
      
      t.addMember(p);
      
      
      session.close();

      printState("addMember - DONE");
      
      JDOUtils.clear(TEAM.class);
      
      printState("remove TEAMS - DONE");
    }
    catch (JDOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }    
  }
  
  private static void printState(String label) throws JDOException {
    System.out.println("------------------------- " + label);
    JDOSession session = JDOSession.open();
    
    Extent<TEAM> teamx = session.getPM().getExtent(TEAM.class);
    for (TEAM team : teamx) {
      System.out.println("+ TEAM: ["+team.name+"], id=["+session.getPM().getObjectId(team)+"]");
    }
    
    Extent<PERSON> personx = session.getPM().getExtent(PERSON.class);
    for (PERSON person : personx) {
      System.out.println("+ PERSON: ["+person.name+"], id=["+session.getPM().getObjectId(person)+"]");
    }
    
    session.close();
    System.out.println();
  }

}
