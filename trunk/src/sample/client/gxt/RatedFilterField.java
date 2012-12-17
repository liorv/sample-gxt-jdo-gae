package sample.client.gxt;

import sample.client.dto.RatedDTO;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class RatedFilterField implements IsWidget
{
  public RatedFilterField(ListView<RatedDTO, String> listView) {
    super();
    this.store = listView.getStore();
  }

  Store<RatedDTO> store;

  @Override
  public Widget asWidget() {
    TextField searchField = new TextField();
    searchField.setEmptyText("Enter Search...");

    TextButton searchClearButton = new TextButton();
    searchClearButton.setIcon(Resources.INSTANCE.cancelSearchButton());
    
    StoreFilterField<RatedDTO> filter = new StoreFilterField<RatedDTO>() {

      @Override
      protected boolean doSelect(Store<RatedDTO> store, RatedDTO parent, RatedDTO item, String filter) {
        String name = item.getName();
        
        name = name.toLowerCase();
        if (name.indexOf(filter.toLowerCase()) != -1) {
          return true;
        }
        return false;
      }
    };
    
    filter.bind(store);
    return filter;
  }
}
