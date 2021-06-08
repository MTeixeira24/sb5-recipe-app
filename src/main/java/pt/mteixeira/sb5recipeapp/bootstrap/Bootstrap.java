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
    public void run(String... args) {
        loadPerfectGuacamole();
        loadSpicyGrilledChickenTacos();
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
        final UnitOfMeasure teaspoon = unitOfMeasureRepository.findByUnit("teaspoon").orElseThrow(IllegalStateException::new);
        final UnitOfMeasure tablespoon = unitOfMeasureRepository.findByUnit("tablespoon").orElseThrow(IllegalStateException::new);

        final Category mexican = categoryRepository.findByDescription("Mexican").orElseThrow(IllegalStateException::new);
        final Category american = categoryRepository.findByDescription("American").orElseThrow(IllegalStateException::new);

        Recipe recipe = new Recipe();

        recipe.addCategory(mexican);
        recipe.addCategory(american);

        recipe.addIngredient(new Ingredient("2", "ancho chili powder", tablespoon, recipe));
        recipe.addIngredient(new Ingredient("1", "dried oregano", teaspoon, recipe));
        recipe.addIngredient(new Ingredient("1", "dried cumin", teaspoon, recipe));
        recipe.addIngredient(new Ingredient("1", "sugar", teaspoon, recipe));
        recipe.addIngredient(new Ingredient("1/2", "salt", teaspoon, recipe));
        recipe.addIngredient(new Ingredient("1", "clove garlic, finely chopped", null, recipe));
        recipe.addIngredient(new Ingredient("1", "finely grated orange zest", tablespoon, recipe));
        recipe.addIngredient(new Ingredient("3", "fresh-squeezed orange juice", tablespoon, recipe));
        recipe.addIngredient(new Ingredient("2", "olive oil", tablespoon, recipe));
        recipe.addIngredient(new Ingredient("4 to 6", "skinless, boneless chicken thighs (1 1/4 pounds)", null, recipe));

        Notes notes = new Notes("Look for ancho chile powder with the Mexican ingredients at your grocery store, " +
                "on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, " +
                "and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        notes.setRecipes(recipe);
        recipe.setNotes(notes);

        recipe.setDescription("Spicy Grilled Chicken Tacos");
        recipe.setCookTime(15);
        recipe.setPrepTime(20);
        recipe.setServings(6);
        recipe.setDifficulty(Difficulty.MEDIUM);
        recipe.setSource("Simply Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe.setDirections("Prepare a gas or charcoal grill for medium-high, direct heat. Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. " +
                "Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest " +
                "part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see " +
                "pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken " +
                "slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");

        recipeRepository.save(recipe);
    }
}
