package jdo.key;

import org.springframework.stereotype.Component;

@Component
public interface IKeyFactory
{
  public <T> String createKey(Class<T> kind, String id);
}
