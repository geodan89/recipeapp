package geo.springframework.recipeapp.services;

import geo.springframework.recipeapp.command.RecipeCommand;
import geo.springframework.recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    RecipeCommand findCommandById(Long id);
    void deleteById(Long id);
}
