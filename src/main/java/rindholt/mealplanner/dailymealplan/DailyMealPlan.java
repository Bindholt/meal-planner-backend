package rindholt.mealplanner.dailymealplan;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantity;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class DailyMealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToMany
    private Set<IngredientWithQuantity> ingredients;

    public DailyMealPlan(LocalDate date) {
        this.date = date;
    }

    public DailyMealPlan(LocalDate date, Set<IngredientWithQuantity> ingredients) {
        this.date = date;
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