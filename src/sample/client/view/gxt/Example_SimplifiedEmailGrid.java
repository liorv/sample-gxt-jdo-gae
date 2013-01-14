package sample.client.view.gxt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import sample.client.view.gxt.Example_EmailInterfaces.Email;
import sample.client.view.gxt.Example_EmailInterfaces.EmailProperties;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
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

public class Example_SimplifiedEmailGrid extends LVGrid<Email, EmailProperties>
{
  public Example_SimplifiedEmailGrid() {
    super();
  }

  @Override
  protected ColumnModel<Email> createModel() {
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

  @Override
  protected ListStore<Email> createListStore() {
    ListStore<Email> store = new ListStore<Email>(props.key());
    //store.addAll(makeEmails());
    return store;
  }

  @Override
  protected ListLoader<ListLoadConfig, ListLoadResult<Email>> createLoader() {
    // no loader, return null == static data
    return null;
  }

  Collection<? extends Email> makeEmails() {
    LinkedList<Email> retval = new LinkedList<Email>();
    final String[] emailTemplate = { "name", "email", "phone", "state", "zip" };
    for (int i = 0; i < 5; i++) {
      retval.add(makeEmail(emailTemplate, i));
    }

    return retval;
  }

  @Override
  public Widget asWidget() {
    super.asWidget();

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

  @Override
  protected EmailProperties createProps() {
    return GWT.create(EmailProperties.class);
  }

}
