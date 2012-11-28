package jdo.key;

import org.springframework.stereotype.Component;

@Component
public class BasicKeyFactory implements IKeyFactory
{

  @Override
  public Object createKey(String kind, String id) {
    return new String(kind+"("+id+")");
  }

  @Override
  public Object createKey(String kind, long id) {
    return new String(kind+"("+id+")");
  }
}
