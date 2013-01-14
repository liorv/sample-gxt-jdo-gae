package sample.shared.action;

import sample.shared.result.ListStatsResult;
import net.customware.gwt.dispatch.shared.Action;

public class GetStats_Group_Action implements Action<ListStatsResult>
{
  public String group;

  // for serialization
  public GetStats_Group_Action() {
  }
  
  public GetStats_Group_Action(String group) {
    this.group = group;
  }
}
