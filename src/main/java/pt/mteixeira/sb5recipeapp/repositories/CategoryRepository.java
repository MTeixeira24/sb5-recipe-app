package pt.mteixeira.sb5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.mteixeira.sb5recipeapp.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
