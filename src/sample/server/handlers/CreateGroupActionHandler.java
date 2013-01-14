package sample.server.handlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import jdo.Grouping;
import jdo.JDOSession;
import sample.shared.action.CreateGroupAction;
import sample.shared.result.NullResult;

public class CreateGroupActionHandler implements ActionHandler<CreateGroupAction, NullResult>
{
  @Override
  public NullResult execute(CreateGroupAction action, ExecutionContext context)
      throws DispatchException
  {
    JDOSession session = JDOSession.open();
    Grouping g = new Grouping(action.group.getName());
    session.getPM().makePersistent(g);
    session.close();
    return new NullResult();
  }

  @Override
  public Class<CreateGroupAction> getActionType() {
    return CreateGroupAction.class;
  }

  @Override
  public void rollback(CreateGroupAction action, NullResult stats,
      ExecutionContext context) throws DispatchException
  {}
}
