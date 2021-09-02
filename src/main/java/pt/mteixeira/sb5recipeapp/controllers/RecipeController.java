package pt.mteixeira.sb5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.mteixeira.sb5recipeapp.controllers.exceptions.EntityNotFoundException;
import pt.mteixeira.sb5recipeapp.domain.Recipe;
import pt.mteixeira.sb5recipeapp.services.RecipeService;

import java.util.Optional;
import java.util.StringJoiner;

@Slf4j
@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(path = "/recipe/show/{id}", method = RequestMethod.GET)
    public String getShowRecipePage(Model model,
                                    @PathVariable("id") long id) {
        log.info("operation='getShowRecipePage', msg='Find recipe', id={}", id);
        Optional<Recipe> recipeOptional = recipeService.findById(id);
        Recipe recipe = recipeOptional.orElseThrow(EntityNotFoundException::new);
        model.addAttribute("recipe", recipe);

        StringJoiner stringBuilder = new StringJoiner(", ");
        recipe.getCategories().forEach(category -> stringBuilder.add(category.getDescription()));
        model.addAttribute("categories", stringBuilder.toString());

        return "show";
    }

}
