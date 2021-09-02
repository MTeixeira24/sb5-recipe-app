package pt.mteixeira.sb5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pt.mteixeira.sb5recipeapp.commands.RecipeCommand;
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

    @RequestMapping(path = "/recipe/{id}/show", method = RequestMethod.GET)
    public String getShowRecipePage(Model model,
                                    @PathVariable("id") long id) {
        log.info("operation='getShowRecipePage', msg='Find recipe', id={}", id);
        Optional<Recipe> recipeOptional = recipeService.findById(id);
        Recipe recipe = recipeOptional.orElseThrow(EntityNotFoundException::new);
        model.addAttribute("recipe", recipe);

        StringJoiner stringBuilder = new StringJoiner(", ");
        recipe.getCategories().forEach(category -> stringBuilder.add(category.getDescription()));
        model.addAttribute("categories", stringBuilder.toString());

        return "showRecipe";
    }

    @RequestMapping(path = "/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipeForm";
    }

    @RequestMapping(path = "/recipe/{id}/update")
    public String updateRecipe(Model model,
                               @PathVariable("id") long id) {
        Optional<RecipeCommand> recipeCommandOptional = recipeService.findRecipeCommandById(id);
        RecipeCommand recipeCommand = recipeCommandOptional.orElseThrow(EntityNotFoundException::new);

        model.addAttribute("recipe", recipeCommand);
        return "recipeForm";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand recipeCommand) {
        RecipeCommand savedRecipe = recipeService.saveRecipeCommand(recipeCommand);
        return "redirect:/recipe/" + savedRecipe.getId() + "/show";
    }

}
