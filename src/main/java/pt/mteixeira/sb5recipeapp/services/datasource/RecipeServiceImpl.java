package pt.mteixeira.sb5recipeapp.services.datasource;

import org.springframework.stereotype.Service;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.repositories.RecipeRepository;
import pt.mteixeira.sb5recipeapp.services.RecipeService;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        long size = recipeRepository.count();
        Iterable<Recipe> result = recipeRepository.findAll();
        List<Recipe> recipes = new ArrayList<>((int) size);
        result.forEach(Recipe::getIngredients);
        result.forEach(recipes::add);
        return recipes;
    }
}
