package sample.server;

import java.util.Collection;

abstract public class DTOMapper<T, DTO>
{
  abstract public DTO toDTO(T obj);

  public void toDTO(Iterable<T> coll, Collection<DTO> target) {
    for (T item : coll) {
      target.add(toDTO(item));
    }
  }
}
