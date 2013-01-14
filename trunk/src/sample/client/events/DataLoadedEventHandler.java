package sample.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface DataLoadedEventHandler extends EventHandler
{
  void onLoad(DataLoadedEvent loadEvent);
}
