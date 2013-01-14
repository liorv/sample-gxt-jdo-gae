package sample.shared.result;

import java.io.Serializable;
import java.util.Vector;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class ListStatsResult implements Result, Serializable
{
  public ListStatsResult() {
    stats = new Vector<StatsDTO>();
  }
  
  public Vector<StatsDTO> stats;
}
