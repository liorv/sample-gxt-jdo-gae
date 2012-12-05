package sample.client;

import sample.client.gxt.Example_SimplifiedGrid;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SampleGxtProject implements EntryPoint
{
  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT
      .create(GreetingService.class);

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
    final Button sendButton = new Button("Send");
    final Example_SimplifiedGrid grid = new Example_SimplifiedGrid();

    RootPanel.get("sendButtonContainer").add(grid);

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler, KeyUpHandler
    {
      // Fired when the user clicks on the sendButton.

      public void onClick(ClickEvent event) {
        sendNameToServer();
      }

      // Fired when the user types in the nameField.
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          sendNameToServer();
        }
      }

      // Send the name from the nameField to the server and wait for a response.
      private void sendNameToServer() {

        greetingService.greetServer("text", new AsyncCallback<String>() {
          public void onFailure(Throwable caught) {}

          public void onSuccess(String result) {}
        });
      }
    }

    // Add a handler to send the name to the server
    MyHandler handler = new MyHandler();
    sendButton.addClickHandler(handler);
  }
}
