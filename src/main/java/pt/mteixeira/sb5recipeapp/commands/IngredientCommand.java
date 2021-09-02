package pt.mteixeira.sb5recipeapp.commands;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private String amount;
    private UnitOfMeasureCommand unitOfMeasure;
}
