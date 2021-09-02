package pt.mteixeira.sb5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pt.mteixeira.sb5recipeapp.commands.UnitOfMeasureCommand;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    UnitOfMeasureCommandToUnitOfMeasure victim;

    private static Stream<Arguments> argumentProvider() {
        UnitOfMeasureCommand input1 = new UnitOfMeasureCommand();
        UnitOfMeasure output1 = new UnitOfMeasure();
        input1.setId(1L);
        input1.setUnit("Teaspoon");
        output1.setId(1L);
        output1.setUnit("Teaspoon");

        UnitOfMeasureCommand input2 = new UnitOfMeasureCommand();
        UnitOfMeasure output2 = new UnitOfMeasure();
        input2.setUnit("Pinch");
        output2.setUnit("Pinch");

        UnitOfMeasureCommand input3 = new UnitOfMeasureCommand();
        UnitOfMeasure output3 = new UnitOfMeasure();
        input3.setId(3L);
        output3.setId(3L);

        UnitOfMeasureCommand input4 = new UnitOfMeasureCommand();
        UnitOfMeasure output4 = new UnitOfMeasure();

        return Stream.of(
                Arguments.of(input1, output1),
                Arguments.of(input2, output2),
                Arguments.of(input3, output3),
                Arguments.of(input4, output4),
                Arguments.of(null, null)
        );
    }


    @BeforeEach
    public void setUp() {
        victim = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void shouldConvert(UnitOfMeasureCommand input, UnitOfMeasure expected) {
        UnitOfMeasure actual = victim.convert(input);
        assertEquals(actual, expected);
    }
}