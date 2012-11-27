package jdo;

import java.util.Collection;

public class JDOUtils
{ 
  public static <T> T findAndDetach(Class<T> clz, Object k) {
    JDOSession session = JDOSession.open();   
    
    @SuppressWarnings("unchecked")
    T retval = (T) session.getPM().getObjectById(k);
    
    retval = session.getPM().detachCopy(retval);
    session.close();
    
    return retval;
  }
  
  public static <T> void clear(Class<T> clz) throws JDOException {
    (new ClearByClassAction<T>(clz)).perform();
  }

  public static <T> void persist(T o) throws JDOException {
    (new PersistAction<T>(o)).perform();
  }
  
  public static <T> void persist(Collection<T> coll) throws JDOException {
    (new PersistManyAction<T>(coll)).perform();
  }

}
