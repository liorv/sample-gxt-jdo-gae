package sample.server.handlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import jdo.Category;
import jdo.JDOSession;
import jdo.Rated;
import sample.shared.action.RateAction;
import sample.shared.result.NullResult;

public class RateActionHandler implements ActionHandler<RateAction, NullResult>
{
  @Override
  public NullResult execute(RateAction action, ExecutionContext context)
      throws DispatchException
  {
    JDOSession session = JDOSession.open();
    Category c = session.find(Category.class, action.category);
    Rated r = session.find(Rated.class, action.rated);
    c.rate(r, action.score);
    session.close();
    return new NullResult();
  }

  @Override
  public Class<RateAction> getActionType() {
    return RateAction.class;
  }

  @Override
  public void rollback(RateAction action, NullResult stats,
      ExecutionContext context) throws DispatchException
  {}
}
