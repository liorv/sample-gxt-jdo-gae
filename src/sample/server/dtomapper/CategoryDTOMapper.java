package sample.server.dtomapper;

import sample.client.dto.CategoryDTO;
import jdo.Category;

public class CategoryDTOMapper extends DTOMapper<Category, CategoryDTO>
{
  @Override
  public CategoryDTO toDTO(Category c) {
    CategoryDTO retval = new CategoryDTO(c.getName());
    return retval;
  }

}
