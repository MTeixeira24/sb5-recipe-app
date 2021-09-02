package pt.mteixeira.sb5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pt.mteixeira.sb5recipeapp.commands.UnitOfMeasureCommand;
import pt.mteixeira.sb5recipeapp.domain.UnitOfMeasure;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    UnitOfMeasureToUnitOfMeasureCommand victim;

    private static Stream<Arguments> argumentProvider() {
        UnitOfMeasure input1 = new UnitOfMeasure();
        UnitOfMeasureCommand output1 = new UnitOfMeasureCommand();
        input1.setId(1L);
        input1.setUnit("Teaspoon");
        output1.setId(1L);
        output1.setUnit("Teaspoon");

        UnitOfMeasure input2 = new UnitOfMeasure();
        UnitOfMeasureCommand output2 = new UnitOfMeasureCommand();
        input2.setUnit("Pinch");
        output2.setUnit("Pinch");

        UnitOfMeasure input3 = new UnitOfMeasure();
        UnitOfMeasureCommand output3 = new UnitOfMeasureCommand();
        input3.setId(3L);
        output3.setId(3L);

        UnitOfMeasure input4 = new UnitOfMeasure();
        UnitOfMeasureCommand output4 = new UnitOfMeasureCommand();

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
        victim = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void shouldConvert(UnitOfMeasure input, UnitOfMeasureCommand expected) {
        UnitOfMeasureCommand actual = victim.convert(input);
        assertEquals(actual, expected);
    }
}