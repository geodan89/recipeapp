package geo.springframework.recipeapp.repositories;

import geo.springframework.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {


}
