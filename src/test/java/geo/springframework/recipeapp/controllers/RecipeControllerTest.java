package geo.springframework.recipeapp.controllers;

import geo.springframework.recipeapp.command.RecipeCommand;
import geo.springframework.recipeapp.domain.Recipe;
import geo.springframework.recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    private RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController = new RecipeController(recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void showRecipeByIdTest() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

//    @Test
//    void recipeNotFoundTest() throws Exception {
//        when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);
//
//        mockMvc.perform(get("/recipe/1/show"))
//                .andExpect(status().isNotFound())
//                .andExpect(view().name("404 error"));
//    }

//    @Test
//    void showRecipeNumberFormatExceptionTest() throws Exception {
//
//        mockMvc.perform(get("recipe/asfd/show"))
//                .andExpect(status().isBadRequest())
//                .andExpect(view().name("404error"));
//    }

    @Test
    void getNewRecipeFormTest() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void postNewRecipeFormTest() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(4L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommand);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
                .param("description", "description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/4/show"));
    }


}