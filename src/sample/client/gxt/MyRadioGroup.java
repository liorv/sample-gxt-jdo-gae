package sample.client.gxt;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.form.Radio;

public class MyRadioGroup<T> implements IsWidget
{
  static class ToStringLabelProvider<T> implements LabelProvider<T>
  {
    @Override
    public String getLabel(T item) {
      return item.toString();
    }
  }

  protected List<Radio> buttons = new Vector<Radio>();

  protected int defaultIndex = 0;

  private Container c;

  public MyRadioGroup(String groupName, Container c, List<T> options)
  {
    init(groupName, c, options, new ToStringLabelProvider<T>());
  }
  
  public MyRadioGroup(String groupName, Container c, List<T> options,
      LabelProvider<T> labelProvider)
  {
    init(groupName, c, options, labelProvider);
  }

  private void init(String groupName, Container c, List<T> options,
      LabelProvider<T> labelProvider)
  {
    this.c = c;
    for (T option : options) {
      Radio radio = new Radio();
      radio.setName(groupName);
      radio.setBoxLabel(labelProvider.getLabel(option));
      c.add(radio);
      buttons.add(radio);
    }
  }

  @Override
  public Widget asWidget() {
    return c;
  }

}
