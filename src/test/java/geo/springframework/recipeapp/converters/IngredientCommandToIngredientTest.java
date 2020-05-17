package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.IngredientCommand;
import geo.springframework.recipeapp.command.UnitOfMeasureCommand;
import geo.springframework.recipeapp.domain.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class IngredientCommandToIngredientTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTON = "description";
    private static final BigDecimal AMOUNT = new BigDecimal(100);
    private static final Long UNIT_OF_MEASURE_ID = 2L;


    private IngredientCommandToIngredient converter;

    @BeforeEach
    void setUp() {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    void returnsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    void returnsEmptyObject(){
        assertNotNull(converter.convert(new IngredientCommand()));
    }

    @Test
    void convert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription(DESCRIPTON);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setId(ID_VALUE);
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UNIT_OF_MEASURE_ID);
        ingredientCommand.setUnitOfMeasure(unitOfMeasureCommand);


        final Ingredient ingredient = converter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTON, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UNIT_OF_MEASURE_ID, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    void convertWithNullUnitOfMeasure() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setDescription(DESCRIPTON);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setId(ID_VALUE);

        final Ingredient ingredient = converter.convert(ingredientCommand);

        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(ID_VALUE, ingredient.getId());
        assertEquals(DESCRIPTON, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}