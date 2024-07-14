package rindholt.mealplanner.recipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rindholt.mealplanner.dailymealplaningredient.DailyMealPlanIngredient;

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
    private Set<DailyMealPlanIngredient> ingredients;

    public Recipe(String title, String procedure, Long minutes, String imageURL, Long portions, Set<DailyMealPlanIngredient> ingredients){
        this.title = title;
        this.guide = procedure;
        this.minutes = minutes;
        this.imageURL = imageURL;
        this.portions = portions;
        this.ingredients = ingredients;
    }

    public void addIngredient(DailyMealPlanIngredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(DailyMealPlanIngredient ingredient) {
        ingredients.remove(ingredient);
    }

    public void clearIngredients() {
        ingredients.clear();
    }
}


