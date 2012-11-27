package jdo;

public class ClearByClassAction<T> extends JDOAction
{
  private Class<T> clz;

  public ClearByClassAction(Class<T> clz) {
    this.clz = clz;
  }

  @Override
  public void doIt(JDOSession session) throws JDOException{
    session.clear(clz);
  }

  @Override
  public boolean isTransactional() {
    return false;
  }

}
