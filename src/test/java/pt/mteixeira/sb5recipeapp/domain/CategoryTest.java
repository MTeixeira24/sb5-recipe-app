package pt.mteixeira.sb5recipeapp.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @MockBean
    private Recipe recipe1;

    @MockBean
    private Recipe recipe2;

    @BeforeEach
    public void setUp() {
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

}