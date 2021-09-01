package pt.mteixeira.sb5recipeapp.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import pt.mteixeira.sb5recipeapp.controllers.exceptions.ControllerExceptionHandler;
import pt.mteixeira.sb5recipeapp.controllers.exceptions.EntityNotFoundException;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.services.RecipeService;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    public static final long RECIPE_ID_1 = 1L;
    public static final long RECIPE_ID_2 = 2L;

    Recipe recipe1;
    Recipe recipe2;
    Set<Recipe> recipes;
    MockMvc mockMvc;

    @InjectMocks
    RecipeController victim;

    @Mock
    Model model;
    @Mock
    RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipe1 = new Recipe();
        recipe1.setId(RECIPE_ID_1);
        recipe2 = new Recipe();
        recipe2.setId(RECIPE_ID_2);
        recipes = Sets.newSet(recipe1, recipe2);
        ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler();

        mockMvc = MockMvcBuilders.standaloneSetup(victim)
                .setControllerAdvice(controllerExceptionHandler)
                .build();
    }

    @Test
    void shouldSucceedInReturningRecipeIfFound() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(Optional.of(recipe2));
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/" + RECIPE_ID_2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("show"))
                .andExpect(MockMvcResultMatchers.model().attribute("recipe", recipe2));
    }

    @Test
    void shouldReturn404ErrorPageWhenNotFound() throws Exception {
        when(recipeService.findById(anyLong())).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/show/" + RECIPE_ID_2))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.view().name("notFound"))
                .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("recipe"));
    }

    @Test
    void shouldAddToModelIfFound() {
        when(recipeService.findById(anyLong())).thenReturn(Optional.of(recipe1));
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        String view = victim.getShowRecipePage(model, RECIPE_ID_1);

        assertEquals("show", view);
        verify(model, times(1)).addAttribute(eq("recipe"), argumentCaptor.capture());
        assertEquals(recipe1, argumentCaptor.getValue());
    }

    @Test
    void shouldThrowIfNotFound() {
        when(recipeService.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> victim.getShowRecipePage(model, RECIPE_ID_1));
        verify(model, times(0)).addAttribute(anyString(), any());
    }
}