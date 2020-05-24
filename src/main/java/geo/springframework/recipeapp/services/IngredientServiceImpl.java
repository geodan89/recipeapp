package geo.springframework.recipeapp.services;

import geo.springframework.recipeapp.command.IngredientCommand;
import geo.springframework.recipeapp.converters.IngredientCommandToIngredient;
import geo.springframework.recipeapp.converters.IngredientToIngredientCommand;
import geo.springframework.recipeapp.domain.Ingredient;
import geo.springframework.recipeapp.domain.Recipe;
import geo.springframework.recipeapp.repositories.RecipeRepository;
import geo.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                                 IngredientCommandToIngredient ingredientCommandToIngredient) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        if(!recipeOptional.isPresent()) {
            log.debug("recipe not found by Id: "+ recipeId);
        }
        Recipe recipe = recipeOptional.get();
        Set<Ingredient> ingredientSet = recipe.getIngredients();
        Optional<IngredientCommand> ingredientCommandOptional = Optional.empty();
        for(Ingredient ingredient : ingredientSet){
            if(ingredient.getId().equals(ingredientId)){
                ingredientCommandOptional = Optional.ofNullable(ingredientToIngredientCommand.convert(ingredient));
            }
        }
        if(!ingredientCommandOptional.isPresent()){
            log.error("ingredient not found by Id: "+ ingredientId);
        }
        return ingredientCommandOptional.get();

    }

    public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand){
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());

        if(!recipeOptional.isPresent()){
            log.debug("recipe not found by Id: "+ ingredientCommand.getRecipeId());
            return new IngredientCommand();
        }else{
            Recipe recipe = recipeOptional.get();

            Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                Ingredient ingredientFound = ingredientOptional.get();
                ingredientFound.setDescription(ingredientCommand.getDescription());
                ingredientFound.setAmount(ingredientCommand.getAmount());
                ingredientFound.setUnitOfMeasure(unitOfMeasureRepository.findById(ingredientCommand.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("Unit of measure NOT FOUND")));
            }else{
                //add new Ingredient
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                ingredient.setRecipe(recipe);
                recipe.addIngredient(ingredient);
            }

            Recipe savedRecipe = recipeRepository.save(recipe);

            Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(savedRecipeIngredient -> savedRecipeIngredient.getId().equals(ingredientCommand.getId()))
                    .findFirst();

            if(!savedIngredientOptional.isPresent()){
                savedIngredientOptional = savedRecipe.getIngredients().stream()
                        .filter(recipeIngredients -> recipeIngredients.getDescription().equals(ingredientCommand.getDescription()))
                        .filter(recipeIngredients -> recipeIngredients.getAmount().equals(ingredientCommand.getAmount()))
                        .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
                        .findFirst();
            }
            return ingredientToIngredientCommand.convert(savedIngredientOptional.get());
        }




    }
}
