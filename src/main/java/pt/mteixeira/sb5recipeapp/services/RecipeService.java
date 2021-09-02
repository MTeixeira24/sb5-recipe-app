package pt.mteixeira.sb5recipeapp.services;

import pt.mteixeira.sb5recipeapp.commands.RecipeCommand;
import pt.mteixeira.sb5recipeapp.domain.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Optional<Recipe> findById(long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
