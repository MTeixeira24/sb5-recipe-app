package pt.mteixeira.sb5recipeapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.collections.Sets;
import org.springframework.ui.Model;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.services.RecipeService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IndexControllerTest {

    IndexController victim;

    Recipe recipe;

    @Mock
    Model model;
    @Mock
    RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        victim = new IndexController(recipeService);
        recipe = new Recipe();
        recipe.setId(1L);
    }

    @Test
    public void shouldInteractWithModel() {
        Set<Recipe> recipes = Sets.newSet(recipe);
        when(recipeService.getAllRecipes()).thenReturn(recipes);
        victim.getIndexPage(model);
        verify(recipeService, times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute("recipes", recipes);
    }

    @Test
    public void shouldReturnCorrectString() {
        Set<Recipe> recipes = Sets.newSet(recipe);
        when(recipeService.getAllRecipes()).thenReturn(recipes);
        String actual = victim.getIndexPage(model);
        assertEquals("index", actual);
    }

}