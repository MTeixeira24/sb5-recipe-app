package pt.mteixeira.sb5recipeapp.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class IngredientCommand {
    private Long id;
    private String description;
    private String amount;
    private UnitOfMeasureCommand unitOfMeasure;
}
