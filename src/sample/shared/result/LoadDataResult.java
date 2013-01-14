package sample.shared.result;

import java.io.Serializable;
import java.util.Vector;

import net.customware.gwt.dispatch.shared.Result;

@SuppressWarnings("serial")
public class LoadDataResult implements Result, Serializable
{
  public LoadDataResult() {
    stats = new Vector<StatsDTO>();
    allRated = new Vector<RatedDTO>();
    allCategories = new Vector<CategoryDTO>();
  }
  
  public Vector<StatsDTO> stats;
  
  public Vector<RatedDTO> allRated;

  public Vector<CategoryDTO> allCategories;
}
