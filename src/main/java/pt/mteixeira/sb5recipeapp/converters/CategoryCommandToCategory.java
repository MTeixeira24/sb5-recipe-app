package pt.mteixeira.sb5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pt.mteixeira.sb5recipeapp.commands.CategoryCommand;
import pt.mteixeira.sb5recipeapp.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand == null) {
            return null;
        }

        return Category.builder()
                .Id(categoryCommand.getId())
                .description(categoryCommand.getDescription())
                .build();
    }
}
