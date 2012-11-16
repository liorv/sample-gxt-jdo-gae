package jdo;

import java.util.List;

import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;

public class GAEUtils
{
  public static <T> List<T> find(Class<T> clz, Key ancestorKey)
      throws JDOException
  {
    JDOSession session = JDOSession.open();

    Query q =
        session.getPM().newQuery(
            "select from " + clz.getName() + " where parentKey == pkParam "
                + "parameters Key pkParam");
    q.declareImports("import com.google.appengine.api.datastore.Key");
    
    @SuppressWarnings("unchecked")
    List<T> retval =
        (List<T>) q.execute(ancestorKey);

    session.close();
    return retval;
  }
}
