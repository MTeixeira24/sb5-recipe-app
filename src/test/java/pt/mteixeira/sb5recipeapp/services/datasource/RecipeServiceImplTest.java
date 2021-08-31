package pt.mteixeira.sb5recipeapp.services.datasource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.repositories.RecipeRepository;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

    private RecipeServiceImpl victim;

    @Mock
    RecipeRepository recipeRepository;

    Recipe recipe1;
    Recipe recipe2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe2 = new Recipe();
        recipe2.setId(2L);

        victim = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void shouldQueryRepositoryForRecipes() {
        Set<Recipe> recipes = Sets.newSet(recipe1, recipe2);
        when(recipeRepository.findAll()).thenReturn(recipes);
        Set<Recipe> actual = victim.getAllRecipes();
        assertEquals(recipes, actual);
    }
}