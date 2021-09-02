package pt.mteixeira.sb5recipeapp.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pt.mteixeira.sb5recipeapp.commands.CategoryCommand;
import pt.mteixeira.sb5recipeapp.domain.Category;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryCommandToCategoryTest {

    CategoryCommandToCategory victim;

    private static Stream<Arguments> argumentProvider() {
        CategoryCommand categoryCommand1 = CategoryCommand.builder().build();
        Category category1 = Category.builder().build();

        CategoryCommand categoryCommand2 = CategoryCommand.builder()
                .id(1L)
                .build();
        Category category2 = Category.builder()
                .Id(1L)
                .build();

        CategoryCommand categoryCommand3 = CategoryCommand.builder()
                .description("category")
                .build();
        Category category3 = Category.builder()
                .description("category")
                .build();

        CategoryCommand categoryCommand4 = CategoryCommand.builder()
                .id(3L)
                .description("category")
                .build();
        Category category4 = Category.builder()
                .Id(3L)
                .description("category")
                .build();

        return Stream.of(
                Arguments.of(categoryCommand1, category1),
                Arguments.of(categoryCommand2, category2),
                Arguments.of(categoryCommand3, category3),
                Arguments.of(categoryCommand4, category4),
                Arguments.of(null, null)
        );
    }

    @BeforeEach
    void setUp() {
        victim = new CategoryCommandToCategory();
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void shouldConvert(CategoryCommand input, Category expected) {
        Category actual = victim.convert(input);
        assertEquals(expected, actual);
    }
}