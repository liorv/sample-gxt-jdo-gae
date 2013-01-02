package sample.server.dtomapper;

import jdo.StatRelation;
import sample.shared.result.StatsDTO;

public class StatsDTOMapper extends DTOMapper<StatRelation, StatsDTO>
{
  @Override
  public StatsDTO toDTO(StatRelation stat) {
    StatsDTO retval =
        new StatsDTO(stat.getCategory().getName(), stat.getName(),
            stat.getMin(), stat.getMax(), stat.getMean(), stat.getCount());
    return retval;
  }
}
