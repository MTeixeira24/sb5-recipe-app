package pt.mteixeira.sb5recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.util.collections.Sets;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class CategoryTest {

    private Category category;

    @Mock
    private Recipe recipe1;

    @Mock
    private Recipe recipe2;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        category = new Category();
    }

    @Test
    public void shouldGetId() {
        long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }

    @Test
    public void shouldGetDescription() {
        String description = "Some description";
        category.setDescription(description);
        assertEquals(description, category.getDescription());
    }

    @Test
    public void shouldGetRecipes() {
        Set<Recipe> recipeSet = Sets.newSet(recipe1, recipe2);
        category.setRecipes(recipeSet);
        assertTrue(category.getRecipes().containsAll(recipeSet));
    }

    @Test
    public void toStringToNotOverflow() {
        Category category = new Category();
        category.setId(1L);
        category.setRecipes(Sets.newSet(recipe1));
        category.setDescription("Category");

        when(recipe1.toString()).thenThrow(new StackOverflowError());

        String actual = category.toString();
        System.out.println(actual);

        assertTrue(actual.contains("Id=1"));
        assertTrue(actual.contains("description=Category"));
        assertFalse(actual.contains("recipes"));
    }

}