package sample.client.view.gxt;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;

public class MyComboBox<T> extends SimpleComboBox<T>
{
  static class ToStringLabelProvider<T> implements LabelProvider<T>
  {
    @Override
    public String getLabel(T item) {
      return item.toString();
    }
  }

  protected List<T> options;

  protected int defaultIndex = 0;

  public MyComboBox(List<T> options) {
    super(new ToStringLabelProvider<T>());
    this.options = options;
  }

  public List<T> getOptions() {
    return options;
  }

  public void setDefaultIndex(int idx) {
    if (idx >= 0 && idx <= options.size()) defaultIndex = idx;
  }

  @Override
  public Widget asWidget() {
    add(options);
    setForceSelection(true);
    setEditable(false);
    setTriggerAction(TriggerAction.ALL);
    setValue(options.get(defaultIndex), true, true);

    return super.asWidget();
  }

}
