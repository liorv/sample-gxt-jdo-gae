package sample.client.gxt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sample.client.dto.StatsDTO;
import sample.client.gxt.StatInterfaces.StatRelationCategoryRatedProps;

import com.google.gwt.core.client.GWT;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;

public class RatedCategoryGrid extends LVGrid<StatsDTO, StatRelationCategoryRatedProps>
{
  public RatedCategoryGrid() {
    super();
  }

  @Override
  protected ColumnModel<StatsDTO> createModel() {
    List<ColumnConfig<StatsDTO, ?>> l =
        new ArrayList<ColumnConfig<StatsDTO, ?>>();
    l.add(new ColumnConfig<StatsDTO, String>(props.name(), 100, "Name"));
    l.add(new ColumnConfig<StatsDTO, String>(props.category(), 100, "Category"));
    l.add(new ColumnConfig<StatsDTO, Float>(props.min(), 100, "Minimum"));
    l.add(new ColumnConfig<StatsDTO, Float>(props.max(), 100, "Maximum"));
    l.add(new ColumnConfig<StatsDTO, Float>(props.mean(), 100, "Average"));
    l.add(new ColumnConfig<StatsDTO, Integer>(props.count(), 100, "Votes"));

    ColumnModel<StatsDTO> cm = new ColumnModel<StatsDTO>(l);

    return cm;
  }

  @Override
  protected ListStore<StatsDTO> createListStore() {
    if (listStore == null) {
      listStore = new ListStore<StatsDTO>(props.key());
    }
    return listStore;
  }

  @Override
  protected ListLoader<ListLoadConfig, ListLoadResult<StatsDTO>> createLoader()
  {
    return null;
  }

  public void setStats(Collection<StatsDTO> stats) {
    listStore.clear();
    listStore.addAll(stats);
  }

  @Override
  protected StatRelationCategoryRatedProps createProps() {
    return GWT.create(StatRelationCategoryRatedProps.class);
  }

}
