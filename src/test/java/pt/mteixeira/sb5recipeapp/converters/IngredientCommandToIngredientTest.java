package pt.mteixeira.sb5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pt.mteixeira.sb5recipeapp.commands.IngredientCommand;
import pt.mteixeira.sb5recipeapp.commands.UnitOfMeasureCommand;
import pt.mteixeira.sb5recipeapp.domain.Ingredient;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class IngredientCommandToIngredientTest {

    private final static UnitOfMeasure unitOfMeasure = UnitOfMeasure.builder()
            .unit("Unit")
            .Id(10L)
            .build();
    private final static UnitOfMeasureCommand unitOfMeasureCommand = UnitOfMeasureCommand.builder()
            .unit("Unit")
            .id(10L)
            .build();
    IngredientCommandToIngredient victim;
    @Mock
    UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

    private static Stream<Arguments> argumentProvider() {
        Ingredient ingredient1 = Ingredient.builder()
                .amount("amount")
                .description("description")
                .unitOfMeasure(unitOfMeasure)
                .Id(1L)
                .build();
        IngredientCommand ingredientCommand1 = IngredientCommand.builder()
                .amount("amount")
                .description("description")
                .unitOfMeasure(unitOfMeasureCommand)
                .id(1L)
                .build();

        Ingredient ingredient2 = Ingredient.builder().build();
        IngredientCommand ingredientCommand2 = IngredientCommand.builder().build();

        return Stream.of(
                Arguments.of(ingredientCommand1, ingredient1),
                Arguments.of(ingredientCommand2, ingredient2),
                Arguments.of(null, null)
        );
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(unitOfMeasureCommandToUnitOfMeasure.convert(eq(unitOfMeasureCommand))).thenReturn(unitOfMeasure);
        victim = new IngredientCommandToIngredient(unitOfMeasureCommandToUnitOfMeasure);
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    public void shouldConvert(IngredientCommand input, Ingredient expected) {
        assertEquals(expected, victim.convert(input));
    }
}