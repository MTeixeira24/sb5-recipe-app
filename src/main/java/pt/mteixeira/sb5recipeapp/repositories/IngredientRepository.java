package pt.mteixeira.sb5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.mteixeira.sb5recipeapp.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
