package rindholt.mealplanner.recipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantity;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String guide;
    private Long minutes;
    private String imageURL;
    private Long portions;
    @ManyToMany
    private Set<IngredientWithQuantity> ingredients;

    public Recipe(String title, String guide, Long minutes, String imageURL, Long portions, Set<IngredientWithQuantity> ingredients){
        this.title = title;
        this.guide = guide;
        this.minutes = minutes;
        this.imageURL = imageURL;
        this.portions = portions;
        this.ingredients = ingredients;
    }

    public void addIngredient(IngredientWithQuantity ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(IngredientWithQuantity ingredient) {
        ingredients.remove(ingredient);
    }

    public void clearIngredients() {
        ingredients.clear();
    }
}


