package pt.mteixeira.sb5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.mteixeira.sb5recipeapp.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
