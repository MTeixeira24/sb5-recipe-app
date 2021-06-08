package pt.mteixeira.sb5recipeapp.services;

import pt.mteixeira.sb5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

}
