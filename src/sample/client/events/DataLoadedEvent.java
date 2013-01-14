package sample.client.events;

import sample.shared.result.LoadDataResult;

import com.google.gwt.event.shared.GwtEvent;

public class DataLoadedEvent extends GwtEvent<DataLoadedEventHandler>
{
  public static Type<DataLoadedEventHandler> TYPE = new Type<DataLoadedEventHandler>();
  
  private LoadDataResult result;

  public DataLoadedEvent(LoadDataResult result) {
    this.result = result;
  }

  public LoadDataResult getResult() {
    return result;
  }

  @Override
  public Type<DataLoadedEventHandler> getAssociatedType()
  {
    return TYPE;
  }

  @Override
  protected void dispatch(DataLoadedEventHandler handler) {
    handler.onLoad(this);    
  }

}
