package geo.springframework.recipeapp.converters;

import geo.springframework.recipeapp.command.RecipeCommand;
import geo.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final CategoryToCategoryCommand  categoryToCategoryCommand;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(CategoryToCategoryCommand categoryToCategoryCommand,
                                 IngredientToIngredientCommand ingredientToIngredientCommand,
                                 NotesToNotesCommand notesToNotesCommand) {
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }


    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();

        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setId(recipe.getId());
        recipeCommand.setImage(recipe.getImage());
        recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getNotes()));
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());

        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0){
            recipe.getIngredients()
                    .forEach(ingredient ->
                            recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(ingredient)));
        }

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0){
            recipe.getCategories()
                    .forEach(category ->
                            recipeCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
        }

        return recipeCommand;
    }
}
