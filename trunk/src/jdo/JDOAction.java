package jdo;

import org.apache.log4j.Logger;

abstract public class JDOAction
{
  private static Logger log = Logger.getLogger(JDOAction.class);
  
  protected JDOAction() {}

  public abstract void doIt(JDOSession session) throws JDOException;

  public abstract boolean isTransactional();

  public void perform() throws JDOException {
    JDOSession session = null;

    if(log.isDebugEnabled())
      log.debug("perform(" + this.getClass().getSimpleName() + ")");
    try {
      session = JDOSession.open(isTransactional());
      doIt(session);
      session.close();
    }
    catch (JDOException lv) {
      lv.printStackTrace();
      throw lv;
    }
  }
}
