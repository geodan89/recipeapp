package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.UnitOfMeasureCommand;
import geo.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long LONG_VALUE = 1L;
    private static final String DESCRIPTION = "description";

    private UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void returnsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    void returnsEmptyObjectTest(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setId(LONG_VALUE);
        unitOfMeasure.setDescription(DESCRIPTION);

        final UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(unitOfMeasure);

        assertEquals(LONG_VALUE, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}