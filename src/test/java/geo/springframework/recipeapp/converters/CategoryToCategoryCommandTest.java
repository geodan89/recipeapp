package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.CategoryCommand;
import geo.springframework.recipeapp.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryToCategoryCommandTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTON = "description";

    private CategoryToCategoryCommand converter;

    @BeforeEach
    void setUp() {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    void returnsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    void returnsEmptyObjectTest(){
        assertNotNull(converter.convert(new Category()));
    }

    @Test
    void convert() {
        Category category = new Category();
        category.setId(LONG_VALUE);
        category.setDescription(DESCRIPTON);

        final CategoryCommand categoryCommand = converter.convert(category);

        assertEquals(LONG_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTON, categoryCommand.getDescription());
    }
}