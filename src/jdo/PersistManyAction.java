package jdo;

import java.util.Collection;

public class PersistManyAction<T> extends JDOAction
{
  private Collection<T> coll;

  public PersistManyAction(Collection<T> coll) {
    this.coll = coll;
  }

  @Override
  public void doIt(JDOSession session) throws JDOException {
    session.persist(coll);
  }

  @Override
  public boolean isTransactional() {
    return true;
  }

}
