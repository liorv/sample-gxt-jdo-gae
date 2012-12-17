package sample.client.gxt;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

abstract public class LVGrid<POJO, POJO_PROPS extends PropertyAccess<POJO>> implements IsWidget
{
  protected ColumnModel<POJO> columnModel;

  protected ListStore<POJO> listStore;

  protected Grid<POJO> grid;

  protected POJO_PROPS props;

  protected AutoBeanFactory factory;

  public LVGrid() {
  }

  @Override
  public Widget asWidget() {
    props = createProps();

    factory = GWT.create(AutoBeanFactory.class);

    columnModel = createModel();

    listStore = createListStore();

    ListLoader<ListLoadConfig, ListLoadResult<POJO>> loader = createLoader();

    grid = new Grid<POJO>(listStore, columnModel);

    if (loader != null) {
      grid.setLoader(loader);
    }
    
    return grid;
  }

  abstract protected ColumnModel<POJO> createModel();
  
  abstract protected POJO_PROPS createProps();

  abstract protected ListStore<POJO> createListStore();

  abstract protected ListLoader<ListLoadConfig, ListLoadResult<POJO>> createLoader();
}
