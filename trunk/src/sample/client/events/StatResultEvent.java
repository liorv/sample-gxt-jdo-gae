package sample.client.events;

import java.util.List;

import sample.shared.result.StatsDTO;

import com.google.gwt.event.shared.GwtEvent;

public class StatResultEvent extends GwtEvent<StatResultEventHandler>
{
  public static Type<StatResultEventHandler> TYPE =
      new Type<StatResultEventHandler>();

  public List<StatsDTO> result;

  public StatResultEvent(List<StatsDTO> result) {
    this.result = result;
  }

  @Override
  public Type<StatResultEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(StatResultEventHandler handler) {
    handler.onStatResult(this);
  }

}
