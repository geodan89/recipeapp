package geo.springframework.recipeapp.services;

import geo.springframework.recipeapp.converters.RecipeCommandToRecipe;
import geo.springframework.recipeapp.converters.RecipeToRecipeCommand;
import geo.springframework.recipeapp.domain.Recipe;
import geo.springframework.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeToRecipeCommand, recipeCommandToRecipe);
    }

    @Test
    void getRecipesTest() {

       Recipe recipe = new Recipe();
       HashSet recipeData = new HashSet();
       recipeData.add(recipe);

       when(recipeRepository.findAll()).thenReturn(recipeData);

       Set<Recipe> recipes = recipeService.getRecipes();

       Assertions.assertEquals(recipes.size(), 1);

       verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findByIdTest(){
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.findById(1L);

        Assertions.assertNotNull(returnedRecipe);

        verify(recipeRepository, times(1)).findById(anyLong());

        verify(recipeRepository,never()).findAll();
    }


}