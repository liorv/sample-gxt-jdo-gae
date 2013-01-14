package sample.server.dtomapper;

import java.util.HashSet;
import java.util.Set;

import sample.server.DTOMapper;
import sample.shared.result.CategoryDTO;
import sample.shared.result.GroupDTO;
import sample.shared.result.RatedDTO;
import jdo.Grouping;

public class GroupDTOMapper extends DTOMapper<Grouping, GroupDTO>
{
  @Override
  public GroupDTO toDTO(Grouping g) {
    Set<RatedDTO> membersDTO = new HashSet<RatedDTO>();
    (new RatedDTOMapper()).toDTO(g.getMembers(), membersDTO);

    Set<CategoryDTO> categoriesDTO = new HashSet<CategoryDTO>();
    (new CategoryDTOMapper()).toDTO(g.getCategories(), categoriesDTO);

    return new GroupDTO(g.getName(), membersDTO, categoriesDTO);
  }
}
