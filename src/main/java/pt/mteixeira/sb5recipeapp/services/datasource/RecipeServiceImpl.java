package pt.mteixeira.sb5recipeapp.services.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.mteixeira.sb5recipeapp.commands.RecipeCommand;
import pt.mteixeira.sb5recipeapp.converters.RecipeCommandToRecipe;
import pt.mteixeira.sb5recipeapp.converters.RecipeToRecipeCommand;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.repositories.RecipeRepository;
import pt.mteixeira.sb5recipeapp.services.RecipeService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeToRecipeCommand recipeToRecipeCommand,
                             RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        log.info("operation='getAllRecipes', msg='retrieving all messages'");
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Optional<Recipe> findById(long id) {
        log.info("operation='getById', msg='retrieving recipe by id', id={}", id);
        return recipeRepository.findById(id);
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        Recipe recipe = recipeRepository.save(Objects.requireNonNull(recipeCommandToRecipe.convert(recipeCommand)));
        log.info("operation='saveRecipeCommand', msg='Saved recipe', id={}", recipe.getId());
        return recipeToRecipeCommand.convert(recipe);
    }
}
