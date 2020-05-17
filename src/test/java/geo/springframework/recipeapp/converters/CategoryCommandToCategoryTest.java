package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.CategoryCommand;
import geo.springframework.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCommandToCategoryTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTON = "description";

    private CategoryCommandToCategory converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryCommandToCategory();
    }

    @Test
    void returnsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    void returnsEmptyObjectTest(){
        assertNotNull(converter.convert(new CategoryCommand()));
    }

    @Test
    void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(LONG_VALUE);
        categoryCommand.setDescription(DESCRIPTON);

        final Category category = converter.convert(categoryCommand);

        assertEquals(LONG_VALUE, category.getId());
        assertEquals(DESCRIPTON, category.getDescription());
    }
}