package sample.client.gxt;

import sample.shared.result.NamedDTO;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class NamedFilterField<T extends NamedDTO> implements IsWidget
{
  public NamedFilterField(ListView<T, String> listView) {
    super();
    this.store = listView.getStore();
  }

  Store<T> store;

  @Override
  public Widget asWidget() {
    TextField searchField = new TextField();
    searchField.setEmptyText("Enter Search...");

    TextButton searchClearButton = new TextButton();
    searchClearButton.setIcon(Resources.INSTANCE.cancelSearchButton());
    
    StoreFilterField<T> filter = new StoreFilterField<T>() {

      @Override
      protected boolean doSelect(Store<T> store, T parent, T item, String filter) {
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
