package jdo;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;

public class GAEUtils
{  
  public static <T> List<T> findAll(Class<T> clz, Key ancestorKey)
      throws JDOException
  {
    JDOSession session = JDOSession.open();

    Query q =
        session.getPM().newQuery(
            "select from " + clz.getName());

    @SuppressWarnings("unchecked")
    List<T> retval = (List<T>) q.execute();

    session.close();
    return retval;
  }
}
