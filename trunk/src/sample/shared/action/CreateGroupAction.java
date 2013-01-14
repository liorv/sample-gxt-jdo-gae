package sample.shared.action;

import sample.shared.result.GroupDTO;
import sample.shared.result.NullResult;
import net.customware.gwt.dispatch.shared.Action;

public class CreateGroupAction implements Action<NullResult>
{
  public GroupDTO group;

  // for serialization
  public CreateGroupAction() {
  }
  
  public CreateGroupAction(GroupDTO group) {
    super();
    this.group = group;
  }
}
