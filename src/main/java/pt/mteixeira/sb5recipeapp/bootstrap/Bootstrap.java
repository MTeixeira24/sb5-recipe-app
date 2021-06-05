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
        final UnitOfMeasure teaspoon = unitOfMeasureRepository.findByUnit("teaspoon").get();
        final UnitOfMeasure tablespoon = unitOfMeasureRepository.findByUnit("tablespoon").get();
        final UnitOfMeasure pinch = unitOfMeasureRepository.findByUnit("pinch").get();

        final Category mexican = categoryRepository.findByDescription("Mexican").get();

        Recipe recipe = new Recipe();

        Ingredient i1 = new Ingredient();
        i1.setAmount("2");
        i1.setDescription("ripe avocados");
        i1.setRecipe(recipe);

        Ingredient i2 = new Ingredient();
        i2.setAmount("1/4");
        i2.setDescription("salt, plus more to taste");
        i2.setUnitOfMeasure(teaspoon);
        i2.setRecipe(recipe);

        Ingredient i3 = new Ingredient();
        i3.setAmount("1");
        i3.setDescription("fresh lime or lemon juice");
        i3.setUnitOfMeasure(tablespoon);
        i3.setRecipe(recipe);

        Ingredient i4 = new Ingredient();
        i4.setAmount("2-4");
        i4.setDescription("minced red onion or thinly sliced green onion");
        i4.setUnitOfMeasure(tablespoon);
        i4.setRecipe(recipe);

        Ingredient i5 = new Ingredient();
        i5.setAmount("1-2");
        i5.setDescription("serrano (or jalapeño) chilis, stems and seeds removed, minced");
        i5.setRecipe(recipe);

        Ingredient i6 = new Ingredient();
        i6.setAmount("2");
        i6.setDescription("cilantro (leaves and tender stems), finely chopped");
        i6.setUnitOfMeasure(tablespoon);
        i6.setRecipe(recipe);

        Ingredient i7 = new Ingredient();
        i7.setDescription("freshly ground black pepper");
        i7.setUnitOfMeasure(pinch);
        i7.setRecipe(recipe);

        Ingredient i8 = new Ingredient();
        i8.setAmount("1/2");
        i8.setDescription("ripe tomato, chopped (optional)");
        i8.setRecipe(recipe);

        Ingredient i9 = new Ingredient();
        i9.setDescription("Red radish or jicama slices for garnish (optional)");
        i9.setRecipe(recipe);

        Ingredient i10 = new Ingredient();
        i10.setDescription("Tortilla chips, to serve");
        i10.setRecipe(recipe);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(i1);
        ingredients.add(i2);
        ingredients.add(i3);
        ingredients.add(i4);
        ingredients.add(i5);
        ingredients.add(i6);
        ingredients.add(i7);
        ingredients.add(i8);
        ingredients.add(i9);
        ingredients.add(i10);

        Notes notes = new Notes();
        notes.setRecipeNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves " +
                "are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near " +
                "your eyes for several hours afterwards.");


        recipe.setDescription("Perfect Guacamole");
        recipe.getCategories().add(mexican);
        recipe.setCookTime(10);
        recipe.setPrepTime(10);
        recipe.setServings(4);
        recipe.setDifficulty(Difficulty.EASY);
        recipe.setNotes(notes);
        recipe.setIngredients(ingredients);
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
