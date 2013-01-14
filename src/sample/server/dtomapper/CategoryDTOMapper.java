package sample.server.dtomapper;

import sample.server.DTOMapper;
import sample.shared.result.CategoryDTO;
import jdo.Category;

public class CategoryDTOMapper extends DTOMapper<Category, CategoryDTO>
{
  @Override
  public CategoryDTO toDTO(Category c) {
    CategoryDTO retval = new CategoryDTO(c.getName());
    return retval;
  }

}
