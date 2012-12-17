package sample.client.gxt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.JsonReader;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class Example_JsonEmailGrid implements IsWidget
{

  /*
   * public interface EmailAutoBeanFactory extends AutoBeanFactory {
   * 
   * AutoBean<RecordResult> items();
   * 
   * AutoBean<ListLoadConfig> loadConfig(); }
   */

  public interface Email
  {
    String getName();

    String getEmail();

    String getPhone();

    String getState();

    String getZip();
  }

  /**
   * Defines the structure of the root JSON object being returned by the server.
   * This class is needed as we cannot return a list of objects. Instead, we
   * return a single object with a single property that contains the data
   * records.
   */
  public interface RecordResult
  {

    List<Email> getRecords();
  }

  class DataRecordJsonReader extends JsonReader<ListLoadResult<Email>, RecordResult>
  {
    public DataRecordJsonReader(AutoBeanFactory factory,
        Class<RecordResult> rootBeanType)
    {
      super(factory, rootBeanType);
    }

    @Override
    protected ListLoadResult<Email> createReturnData(Object loadConfig,
        RecordResult incomingData)
    {
      return new ListLoadResultBean<Email>(incomingData.getRecords());
    }
  }

  interface EmailProperties extends PropertyAccess<Email>
  {
    @Path("name")
    ModelKeyProvider<Email> key();

    ValueProvider<Email, String> name();

    ValueProvider<Email, String> email();

    ValueProvider<Email, String> phone();

    ValueProvider<Email, String> state();

    ValueProvider<Email, String> zip();
  }

  @Override
  public Widget asWidget() {
    EmailProperties props = GWT.create(EmailProperties.class);

    // EmailAutoBeanFactory factory = GWT.create(EmailAutoBeanFactory.class);
    AutoBeanFactory factory = GWT.create(AutoBeanFactory.class);

    ColumnModel<Email> cm = createModel(props);

    ListStore<Email> store = new ListStore<Email>(props.key());
    store.addAll(makeEmails());

    ListLoader<ListLoadConfig, ListLoadResult<Email>> loader =
        createLoader(props, factory, store);

    Grid<Email> grid = createGrid(cm, store, loader);

    grid.getView().setForceFit(true);
    grid.setLoadMask(true);
    grid.setBorders(true);
    grid.getView().setEmptyText("Please hit the load button.");

    FramedPanel cp = new FramedPanel();
    cp.setHeadingText("Json Grid Example");
    cp.setCollapsible(true);
    cp.setAnimCollapse(true);
    cp.setWidget(grid);
    cp.setPixelSize(575, 350);
    cp.addStyleName("margin-10");
    cp.setButtonAlign(BoxLayoutPack.CENTER);
    cp.addButton(new TextButton("Load Json", new SelectHandler() {

      @Override
      public void onSelect(SelectEvent event) {
        // TODO loader.load();
      }
    }));

    return cp;
  }

  private Collection<? extends Email> makeEmails() {
    LinkedList<Email> retval = new LinkedList<Email>();
    final String[] emailTemplate = { "name", "email", "phone", "state", "zip" };
    for (int i = 0; i < 5; i++) {
      retval.add(makeEmail(emailTemplate, i));
    }

    return retval;
  }

  private Email makeEmail(final String[] emailTemplate, final int i) {
    return new Email() {

      @Override
      public String getZip() {
        return emailTemplate[4] + i;
      }

      @Override
      public String getState() {
        return emailTemplate[3] + i;
      }

      @Override
      public String getPhone() {
        return emailTemplate[2] + i;
      }

      @Override
      public String getName() {
        return emailTemplate[0] + i;
      }

      @Override
      public String getEmail() {
        return emailTemplate[1] + i;
      }
    };
  }

  private final ListLoader<ListLoadConfig, ListLoadResult<Email>> createLoader(
      EmailProperties props, AutoBeanFactory factory,
      // EmailAutoBeanFactory factory,
      ListStore<Email> store)
  {
    return null;
    /*
     * DataRecordJsonReader reader = new DataRecordJsonReader(factory,
     * RecordResult.class);
     * 
     * String path = "data/data.json"; RequestBuilder builder = new
     * RequestBuilder(RequestBuilder.GET, path);
     * 
     * HttpProxy<ListLoadConfig> proxy = new HttpProxy<ListLoadConfig>(builder);
     * 
     * final ListLoader<ListLoadConfig, ListLoadResult<Email>> loader = new
     * ListLoader<ListLoadConfig, ListLoadResult<Email>>(proxy, reader);
     * 
     * loader.useLoadConfig(factory.create(ListLoadConfig.class).as());
     * 
     * loader .addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig,
     * Email, ListLoadResult<Email>>( store));
     * 
     * return loader;
     */
  }

  private Grid<Email> createGrid(ColumnModel<Email> cm, ListStore<Email> store,
      final ListLoader<ListLoadConfig, ListLoadResult<Email>> loader)
  {
    Grid<Email> grid = new Grid<Email>(store, cm);
    grid.setLoader(loader);
    return grid;
  }

  private ColumnModel<Email> createModel(EmailProperties props) {
    ColumnConfig<Email, String> cc1 =
        new ColumnConfig<Email, String>(props.name(), 100, "Sender");
    ColumnConfig<Email, String> cc2 =
        new ColumnConfig<Email, String>(props.email(), 165, "Email");
    ColumnConfig<Email, String> cc3 =
        new ColumnConfig<Email, String>(props.phone(), 100, "Phone");
    ColumnConfig<Email, String> cc4 =
        new ColumnConfig<Email, String>(props.state(), 50, "State");
    ColumnConfig<Email, String> cc5 =
        new ColumnConfig<Email, String>(props.zip(), 65, "Zip Code");

    List<ColumnConfig<Email, ?>> l = new ArrayList<ColumnConfig<Email, ?>>();
    l.add(cc1);
    l.add(cc2);
    l.add(cc3);
    l.add(cc4);
    l.add(cc5);

    ColumnModel<Email> cm = new ColumnModel<Email>(l);
    return cm;
  }

}
