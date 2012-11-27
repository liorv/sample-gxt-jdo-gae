package jdo;

public class PersistAction<T> extends JDOAction
{
  private T o;

  public PersistAction(T o) {
    this.o = o;
  }

  @Override
  public void doIt(JDOSession session) throws JDOException{
    session.persist(o);
  }

  @Override
  public boolean isTransactional() {
    return true;
  }

}
