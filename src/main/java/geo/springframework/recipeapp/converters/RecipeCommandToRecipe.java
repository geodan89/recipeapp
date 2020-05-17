package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.RecipeCommand;
import geo.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient,
                                 CategoryCommandToCategory categoryCommandToCategory,
                                 NotesCommandToNotes notesCommandToNotes) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setServings(recipeCommand.getServings());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setImage(recipeCommand.getImage());


        if(recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0){
            recipeCommand.getCategories()
                    .forEach(categoryCommand ->
                            recipe.getCategories().add(categoryCommandToCategory.convert(categoryCommand)));
        }

        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients()
                    .forEach(ingredientCommand ->
                            recipe.getIngredients().add(ingredientCommandToIngredient.convert(ingredientCommand)));
        }
        return recipe;
    }
}
