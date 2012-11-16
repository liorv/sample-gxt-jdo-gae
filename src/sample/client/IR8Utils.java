package sample.client;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.Query;
import javax.jdo.annotations.PersistenceAware;

import jdo.DataObject;
import jdo.GAEUtils;
import jdo.JDOException;
import jdo.JDOSession;
import jdo.JDOUtils;

@PersistenceAware
public class IR8Utils
{
 
  static <T> List<T> getData(Class<T> clz) throws JDOException {
    return GAEUtils.find(clz, DataObject.AppKey);
  }

  public static void test() {
    //test1();
    TestUtils.init();
  }
  
  public static void test1() {
    try {
      System.out.println("--------------------------------- START - CLEAR ("
          + getData(Rated.class).size() + ")");
      JDOSession session = JDOSession.open(true);
      Query query = session.getPM().newQuery("DELETE FROM sample.client.Rated");
      Long number = (Long) query.execute();
      System.out.println("    - deleted [" + number + "] entries");
      session.close();
      /*
       * JDOSession session = JDOSession.open(true); session.clear(Rated.class);
       * session.close();
       */

      System.out.println("--------------------------------- DONE - CLEAR ("
          + getData(Rated.class).size() + ")");

      System.out.println("--------------------------------- START - ADD ("
          + getData(Rated.class).size() + ")");
      List<Rated> coll = new LinkedList<Rated>();
      coll.add(new Rated("aaa"));
      coll.add(new Rated("bbb"));
      coll.add(new Rated("ccc"));
      coll.add(new Rated("ddd"));
      coll.add(new Rated("eee"));
      JDOUtils.persist(coll);

      System.out.println("--------------------------------- DONE - ADD ("
          + getData(Rated.class).size() + ")");
      System.out.println("data = " + getData(Rated.class));
    }
    catch (JDOException e) {
      System.out.println("--------------------------------- 1");
      e.printStackTrace();
    }
    catch (Exception e) {
      System.out.println("--------------------------------- 2");
      e.printStackTrace();
    }
  }
}
