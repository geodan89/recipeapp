package geo.springframework.recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @Lob
    private Byte[] image;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category", joinColumns = @JoinColumn(name="recipe_id"), inverseJoinColumns = @JoinColumn(name="category_id"))
    private Set<Category> categories = new HashSet<>();

    public Recipe() {
    }

    public Recipe(String description, Integer prepTime, Integer cookTime, Integer servings, String source, String url, String directions, Set<Ingredient> ingredients, Byte[] image, Difficulty difficulty, Notes notes, Set<Category> categories) {
        this.description = description;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.source = source;
        this.url = url;
        this.directions = directions;
        this.ingredients = ingredients;
        this.image = image;
        this.difficulty = difficulty;
        this.notes = notes;
        this.categories = categories;
    }

    public void addNotes(Notes notes){
        if(notes != null){
            this.notes = notes;
            notes.setRecipe(this);
        }
    }

    public Recipe addIngredient(Ingredient ingredient){
        if(ingredient != null){
            ingredient.setRecipe(this);
            this.ingredients.add(ingredient);
        }
        return this;
    }

    public Recipe deleteIngredient(Ingredient ingredient){
        if(ingredient != null){
            this.ingredients.remove(ingredient);
        }
        return this;
    }
}
