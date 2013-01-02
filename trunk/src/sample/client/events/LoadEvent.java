package sample.client.events;

import com.google.gwt.event.shared.GwtEvent;

public class LoadEvent extends GwtEvent<LoadEventHandler>
{
  public static Type<LoadEventHandler> TYPE = new Type<LoadEventHandler>();

  @Override
  public Type<LoadEventHandler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(LoadEventHandler handler) {
    handler.onLoad(this);    
  }

}
