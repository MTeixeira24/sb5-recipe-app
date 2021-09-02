package pt.mteixeira.sb5recipeapp.services.datasource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import pt.mteixeira.sb5recipeapp.commands.RecipeCommand;
import pt.mteixeira.sb5recipeapp.converters.RecipeCommandToRecipe;
import pt.mteixeira.sb5recipeapp.converters.RecipeToRecipeCommand;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.repositories.RecipeRepository;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

    public static final long RECIPE_ID_1 = 1L;
    public static final long RECIPE_ID_2 = 2L;

    private RecipeServiceImpl victim;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    Recipe recipe1;
    Recipe recipe2;
    Set<Recipe> recipes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe1 = new Recipe();
        recipe1.setId(RECIPE_ID_1);
        recipe2 = new Recipe();
        recipe2.setId(RECIPE_ID_2);
        recipes = Sets.newSet(recipe1, recipe2);

        victim = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    void shouldQueryRepositoryForRecipes() {
        when(recipeRepository.findAll()).thenReturn(recipes);
        Set<Recipe> actual = victim.getAllRecipes();
        assertEquals(recipes, actual);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void shouldQueryRepositoryToFindById() {
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe2));
        Optional<Recipe> actual = victim.findById(RECIPE_ID_2);
        assertTrue(actual.isPresent());
        assertEquals(actual.get(), recipe2);
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldSaveRecipeCommand() {
        Recipe recipe1 = Recipe.builder()
                .description("recipe")
                .build();
        Recipe recipe2 = Recipe.builder()
                .Id(1L)
                .description("recipe")
                .build();


        RecipeCommand recipeCommand1 = RecipeCommand.builder()
                .description("recipe")
                .build();
        RecipeCommand recipeCommand2 = RecipeCommand.builder()
                .id(1L)
                .description("recipe")
                .build();

        when(recipeCommandToRecipe.convert(recipeCommand1)).thenReturn(recipe1);
        when(recipeRepository.save(recipe1)).thenReturn(recipe2);
        when(recipeToRecipeCommand.convert(recipe2)).thenReturn(recipeCommand2);

        RecipeCommand actual = victim.saveRecipeCommand(recipeCommand1);

        assertEquals(recipeCommand2, actual);
    }
}