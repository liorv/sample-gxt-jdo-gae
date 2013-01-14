package sample.server.handlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import jdo.Grouping;
import jdo.JDOSession;
import sample.server.dtomapper.StatsDTOMapper;
import sample.shared.action.GetStats_Group_Action;
import sample.shared.result.ListStatsResult;

public class GetStats_Group_ActionHandler implements ActionHandler<GetStats_Group_Action, ListStatsResult>
{
  @Override
  public ListStatsResult execute(GetStats_Group_Action a, ExecutionContext c)
      throws DispatchException
  {
    JDOSession session = JDOSession.open();
    Grouping g = session.find(Grouping.class, a.group);

    ListStatsResult retval = new ListStatsResult();
    (new StatsDTOMapper()).toDTO(g.getStats(), retval.stats);

    session.close();
    return retval;
  }

  @Override
  public Class<GetStats_Group_Action> getActionType() {
    return GetStats_Group_Action.class;
  }

  @Override
  public void rollback(GetStats_Group_Action a, ListStatsResult r,
      ExecutionContext c) throws DispatchException
  {}

}