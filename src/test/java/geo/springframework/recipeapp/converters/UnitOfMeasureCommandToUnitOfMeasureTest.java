package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.UnitOfMeasureCommand;
import geo.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "description";

    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    void returnsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    void returnsEmptyObjectTest(){
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    void convert() {
        UnitOfMeasureCommand source = new UnitOfMeasureCommand();
        source.setId(LONG_VALUE);
        source.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = converter.convert(source);

        assertEquals(LONG_VALUE, unitOfMeasure.getId());
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());

    }
}