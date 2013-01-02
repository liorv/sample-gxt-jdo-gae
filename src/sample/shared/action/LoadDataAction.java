package sample.shared.action;

import java.io.Serializable;

import sample.shared.Action;
import sample.shared.result.StatsDTO;

@SuppressWarnings("serial")
public class LoadDataAction implements Action<StatsDTO>, Serializable
{
  public LoadDataAction() {
  }
}
