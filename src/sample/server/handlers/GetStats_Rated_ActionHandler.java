package sample.server.handlers;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import jdo.JDOSession;
import jdo.Rated;
import sample.server.dtomapper.StatsDTOMapper;
import sample.shared.action.GetStats_Rated_Action;
import sample.shared.result.ListStatsResult;

public class GetStats_Rated_ActionHandler implements ActionHandler<GetStats_Rated_Action, ListStatsResult>
{
  @Override
  public ListStatsResult execute(GetStats_Rated_Action a, ExecutionContext c)
      throws DispatchException
  {
    JDOSession session = JDOSession.open();
    Rated r = session.find(Rated.class, a.rated);

    ListStatsResult retval = new ListStatsResult();
    (new StatsDTOMapper()).toDTO(r.getStats(), retval.stats);

    session.close();
    return retval;
  }

  @Override
  public Class<GetStats_Rated_Action> getActionType() {
    return GetStats_Rated_Action.class;
  }

  @Override
  public void rollback(GetStats_Rated_Action a, ListStatsResult r,
      ExecutionContext c) throws DispatchException
  {}

}