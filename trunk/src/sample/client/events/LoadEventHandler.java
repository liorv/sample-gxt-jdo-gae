package sample.client.events;

import com.google.gwt.event.shared.EventHandler;

public interface LoadEventHandler extends EventHandler
{
  void onLoad(LoadEvent loadEvent);
}
