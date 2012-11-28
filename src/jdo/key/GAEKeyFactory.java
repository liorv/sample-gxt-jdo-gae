package jdo.key;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.KeyFactory;

@Component
public class GAEKeyFactory implements IKeyFactory
{
  @Override
  public Object createKey(String kind, String id) {
    return KeyFactory.createKey(kind, id);
  }

  @Override
  public Object createKey(String kind, long id) {
    return KeyFactory.createKey(kind, id);
  }

}
