package sample.shared.action;

import sample.shared.result.NullResult;
import net.customware.gwt.dispatch.shared.Action;

public class RateAction implements Action<NullResult>
{
  public String category;
  
  public String rated;
  
  public float score;

  // for serialization
  public RateAction() {
  }

  public RateAction(String category, String rated, float score) {
    super();
    this.category = category;
    this.rated = rated;
    this.score = score;
  }
  
}
