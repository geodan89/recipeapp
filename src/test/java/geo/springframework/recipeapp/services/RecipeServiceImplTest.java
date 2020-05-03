package geo.springframework.recipeapp.services;

import geo.springframework.recipeapp.domain.Recipe;
import geo.springframework.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {

       Recipe recipe = new Recipe();
       HashSet recipeData = new HashSet();
       recipeData.add(recipe);

       when(recipeRepository.findAll()).thenReturn(recipeData);

       Set<Recipe> recipes = recipeService.getRecipes();

       Assertions.assertEquals(recipes.size(), 1);

       verify(recipeRepository, times(1)).findAll();
    }
}