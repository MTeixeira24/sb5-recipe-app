package pt.mteixeira.sb5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pt.mteixeira.sb5recipeapp.domain.Category;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;
import pt.mteixeira.sb5recipeapp.repositories.CategoryRepository;
import pt.mteixeira.sb5recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

@Controller
public class IndexController {


    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUnit("Teaspoon");

        System.out.println("Category is: " + categoryOptional.get().getId());
        System.out.println("UOM is: " + unitOfMeasure.get().getUnit());

        return "index";
    }
}
