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
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesCommandToNotes;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;

    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes,
                                 IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if (recipeCommand == null) {
            return null;
        }

        return Recipe.builder()
                .Id(recipeCommand.getId())
                .description(recipeCommand.getDescription())
                .prepTime(recipeCommand.getPrepTime())
                .cookTime(recipeCommand.getCookTime())
                .servings(recipeCommand.getServings())
                .source(recipeCommand.getSource())
                .url(recipeCommand.getUrl())
                .directions(recipeCommand.getDirections())
                .notes(notesCommandToNotes.convert(recipeCommand.getNotes()))
                .difficulty(recipeCommand.getDifficulty())
                .ingredients(convertIngredients(recipeCommand.getIngredients()))
                .categories(convertCategories(recipeCommand.getCategories()))
                .build();
    }

    private Set<Category> convertCategories(Set<CategoryCommand> categoryCommands) {
        return categoryCommands.stream()
                .map(categoryCommandToCategory::convert)
                .collect(Collectors.toSet());
    }

    private Set<Ingredient> convertIngredients(Set<IngredientCommand> ingredientCommands) {
        return ingredientCommands.stream()
                .map(ingredientCommandToIngredient::convert)
                .collect(Collectors.toSet());
    }
}
