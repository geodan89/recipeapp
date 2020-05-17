package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.CategoryCommand;
import geo.springframework.recipeapp.command.IngredientCommand;
import geo.springframework.recipeapp.command.NotesCommand;
import geo.springframework.recipeapp.command.RecipeCommand;
import geo.springframework.recipeapp.domain.Difficulty;
import geo.springframework.recipeapp.domain.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeCommandToRecipeTest {

    private static final Long ID_VALUE = 1L;
    private static final String DESCRIPTON = "description";
    private static final Integer PREP_TIME = 10;
    private static final Integer COOK_TIME = 20;
    private static final Integer SERVINGS = 4;
    private static final String SOURCE = "Jamila Cuisine";
    private static final String URL = "URL";
    private static final String DIRECTIONS = "Directions";
    private static final Difficulty DIFFICULTY = Difficulty.EASY;
    private static final Long CAT_ID_1 = 5L;
    private static final Long CAT_ID_2 = 6L;
    private static final Long INGR_ID_1 = 7L;
    private static final Long INGR_ID_2 = 9L;
    private static final Long NOTES_ID = 3L;

    private RecipeCommandToRecipe converter;

    @BeforeEach
    void setUp() {
        converter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory(), new NotesCommandToNotes());
    }

    @Test
    void returnsNullTest(){
        assertNull(converter.convert(null));
    }

    @Test
    void returnsEmptyObjectTest(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    void convert() {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(ID_VALUE);
        recipeCommand.setDescription(DESCRIPTON);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setUrl(URL);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setDifficulty(DIFFICULTY);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);
        recipeCommand.setNotes(notes);

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(CAT_ID_1);

        CategoryCommand categoryCommand2 = new CategoryCommand();
        categoryCommand2.setId(CAT_ID_2);

        recipeCommand.getCategories().add(categoryCommand);
        recipeCommand.getCategories().add(categoryCommand2);

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(INGR_ID_1);

        IngredientCommand ingredientCommand2 = new IngredientCommand();
        ingredientCommand2.setId(INGR_ID_2);

        recipeCommand.getIngredients().add(ingredientCommand);
        recipeCommand.getIngredients().add(ingredientCommand2);

        Recipe recipe  = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(ID_VALUE, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(DESCRIPTON, recipe.getDescription());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}