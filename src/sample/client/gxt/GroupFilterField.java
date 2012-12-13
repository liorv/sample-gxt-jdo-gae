package sample.client.gxt;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.form.TextField;

public class GroupFilterField implements IsWidget
{
  public GroupFilterField(ListView<GroupDTO, String> listView) {
    super();
    this.store = listView.getStore();
  }

  Store<GroupDTO> store;

  @Override
  public Widget asWidget() {
    TextField searchField = new TextField();
    searchField.setEmptyText("Enter Search...");

    TextButton searchClearButton = new TextButton();
    searchClearButton.setIcon(Resources.INSTANCE.cancelSearchButton());
    
    StoreFilterField<GroupDTO> filter = new StoreFilterField<GroupDTO>() {

      @Override
      protected boolean doSelect(Store<GroupDTO> store, GroupDTO parent, GroupDTO item, String filter) {
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
