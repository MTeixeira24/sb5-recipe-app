package pt.mteixeira.sb5recipeapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pt.mteixeira.sb5recipeapp.domain.Category;
import pt.mteixeira.sb5recipeapp.domain.Difficulty;
import pt.mteixeira.sb5recipeapp.domain.Ingredient;
import pt.mteixeira.sb5recipeapp.domain.Notes;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;
import pt.mteixeira.sb5recipeapp.repositories.CategoryRepository;
import pt.mteixeira.sb5recipeapp.repositories.IngredientRepository;
import pt.mteixeira.sb5recipeapp.repositories.RecipeRepository;
import pt.mteixeira.sb5recipeapp.repositories.UnitOfMeasureRepository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashSet;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    public Bootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository, RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadPerfectGuacamole();
        //loadSpicyGrilledChickenTacos();
    }

    private void loadPerfectGuacamole() {
        final UnitOfMeasure teaspoon = unitOfMeasureRepository.findByUnit("teaspoon").orElseThrow(IllegalStateException::new);
        final UnitOfMeasure tablespoon = unitOfMeasureRepository.findByUnit("tablespoon").orElseThrow(IllegalStateException::new);
        final UnitOfMeasure pinch = unitOfMeasureRepository.findByUnit("pinch").orElseThrow(IllegalStateException::new);

        final Category mexican = categoryRepository.findByDescription("Mexican").orElseThrow(IllegalStateException::new);
        final Category american = categoryRepository.findByDescription("American").orElseThrow(IllegalStateException::new);

        Recipe recipe = new Recipe();

        recipe.addCategory(mexican);
        recipe.addCategory(american);

        recipe.addIngredient(new Ingredient("2", "ripe avocados", null, recipe));
        recipe.addIngredient(new Ingredient("1/4", "salt, plus more to taste", teaspoon, recipe));
        recipe.addIngredient(new Ingredient("1", "fresh lime or lemon juice", tablespoon, recipe));
        recipe.addIngredient(new Ingredient("2-4", "minced red onion or thinly sliced green onion", tablespoon, recipe));
        recipe.addIngredient(new Ingredient("1-2", "serrano (or jalapeño) chilis, stems and seeds removed, minced", null, recipe));
        recipe.addIngredient(new Ingredient("2", "cilantro (leaves and tender stems), finely chopped", tablespoon, recipe));
        recipe.addIngredient(new Ingredient(null, "freshly ground black pepper", pinch, recipe));
        recipe.addIngredient(new Ingredient("1/2", "ripe tomato, chopped (optional)", null, recipe));
        recipe.addIngredient(new Ingredient(null, "Red radish or jicama slices for garnish (optional)", null, recipe));
        recipe.addIngredient(new Ingredient(null, "Tortilla chips, to serve", null, recipe));

        Notes notes = new Notes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves " +
                "are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near " +
                "your eyes for several hours afterwards.");
        notes.setRecipes(recipe);
        recipe.setNotes(notes);

        recipe.setDescription("Perfect Guacamole");
        recipe.setCookTime(10);
        recipe.setPrepTime(10);
        recipe.setServings(4);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setSource("Simply Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe.setDirections("Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt " +
                "knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n\n\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n\n\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to " +
                "the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in " +
                "their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your " +
                "desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. " +
                "Start with this recipe and adjust to your taste.\n\n\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to " +
                "cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn " +
                "the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla " +
                "chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n" +
                "Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, " +
                "add it just before serving.");

        recipeRepository.save(recipe);

    }

    private void loadSpicyGrilledChickenTacos() {
        throw new NotImplementedException();
    }
}
