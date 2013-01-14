package sample.shared.action;

import sample.shared.result.ListStatsResult;
import net.customware.gwt.dispatch.shared.Action;

public class GetStats_Rated_Action implements Action<ListStatsResult>
{
  public String rated;

  // for serialization
  public GetStats_Rated_Action() {
  }
  
  public GetStats_Rated_Action(String rated) {
    this.rated = rated;
  }
}
