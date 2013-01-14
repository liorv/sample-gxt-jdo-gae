package sample.server.dtomapper;

import jdo.Rated;
import sample.server.DTOMapper;
import sample.shared.result.RatedDTO;

public class RatedDTOMapper extends DTOMapper<Rated, RatedDTO>
{
  @Override
  public RatedDTO toDTO(Rated r) {
    RatedDTO retval = new RatedDTO(r.getName());
    return retval;
  }

}
