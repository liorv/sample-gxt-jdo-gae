package sample.client.gxt;


import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public class StatInterfaces
{
  
  public static interface StatRelationCategoryRatedProps extends PropertyAccess<StatsDTO>
  {
    @Path("category")
    ModelKeyProvider<StatsDTO> key();

    ValueProvider<StatsDTO, String> name();

    ValueProvider<StatsDTO, String> category();

    ValueProvider<StatsDTO, Float> min();

    ValueProvider<StatsDTO, Float> max();

    ValueProvider<StatsDTO, Float> mean();
    
    ValueProvider<StatsDTO, Integer> count();
  }
}
