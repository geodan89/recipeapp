package geo.springframework.recipeapp.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;


class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp(){
        category = new Category();
    }

    @Test
    void getId() {
        Long idValue = 4L;

        category.setId(idValue);

        Assertions.assertEquals(idValue, category.getId());
    }

    @Test
    void getDescription() {

        String description = "Category";
        category.setDescription(description);

        Assertions.assertEquals("Category", category.getDescription());
    }

    @Test
    void getRecipes() {

        HashSet<Recipe> recipes = new HashSet<>();
        category.setRecipes(recipes);

        Assertions.assertEquals(recipes, category.getRecipes());
    }
}