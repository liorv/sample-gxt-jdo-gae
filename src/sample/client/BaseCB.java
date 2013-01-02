package sample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class BaseCB<T> implements AsyncCallback<T>
{

  @Override
  public void onFailure(Throwable caught) {
  }

  @Override
  public void onSuccess(T result) {
  }

}
