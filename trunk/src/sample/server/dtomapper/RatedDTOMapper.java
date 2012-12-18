package sample.server.dtomapper;

import jdo.Rated;
import sample.client.dto.RatedDTO;

public class RatedDTOMapper extends DTOMapper<Rated, RatedDTO>
{
  @Override
  public RatedDTO toDTO(Rated r) {
    RatedDTO retval = new RatedDTO(r.getName());
    return retval;
  }

}
