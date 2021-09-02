package pt.mteixeira.sb5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pt.mteixeira.sb5recipeapp.commands.CategoryCommand;
import pt.mteixeira.sb5recipeapp.commands.IngredientCommand;
import pt.mteixeira.sb5recipeapp.commands.RecipeCommand;
import pt.mteixeira.sb5recipeapp.domain.Category;
import pt.mteixeira.sb5recipeapp.domain.Ingredient;
import pt.mteixeira.sb5recipeapp.domain.Recipe;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notesToNotesCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;

    public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 CategoryToCategoryCommand categoryToCategoryCommand) {
        this.notesToNotesCommand = notesToNotesCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }


    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        return RecipeCommand.builder()
                .id(recipe.getId())
                .description(recipe.getDescription())
                .prepTime(recipe.getPrepTime())
                .cookTime(recipe.getCookTime())
                .servings(recipe.getServings())
                .source(recipe.getSource())
                .url(recipe.getUrl())
                .directions(recipe.getDirections())
                .notes(notesToNotesCommand.convert(recipe.getNotes()))
                .difficulty(recipe.getDifficulty())
                .ingredients(convertIngredients(recipe.getIngredients()))
                .categories(convertCategories(recipe.getCategories()))
                .build();
    }

    private Set<CategoryCommand> convertCategories(Set<Category> categories) {
        return categories.stream()
                .map(categoryToCategoryCommand::convert)
                .collect(Collectors.toSet());
    }

    private Set<IngredientCommand> convertIngredients(Set<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredientToIngredientCommand::convert)
                .collect(Collectors.toSet());
    }
}
