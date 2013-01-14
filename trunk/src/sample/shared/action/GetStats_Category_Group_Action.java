package sample.shared.action;

import sample.shared.result.ListStatsResult;
import net.customware.gwt.dispatch.shared.Action;

public class GetStats_Category_Group_Action implements Action<ListStatsResult>
{
  public String category;
  
  // for serialization
  public GetStats_Category_Group_Action() {
  }

  public GetStats_Category_Group_Action(String category) {
    this.category = category;
  }
}
