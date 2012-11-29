package jdo.key;

import org.springframework.stereotype.Component;

import com.google.appengine.api.datastore.KeyFactory;

@Component
public class GAEKeyFactory implements IKeyFactory
{
  @Override
  public <T> String createKey(Class<T> clz, String id) {
    return KeyFactory.createKeyString(clz.getSimpleName(), id);
  }

}
