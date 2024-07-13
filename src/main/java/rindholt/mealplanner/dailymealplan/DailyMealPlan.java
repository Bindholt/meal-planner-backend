package rindholt.mealplanner.dailymealplan;

import com.fasterxml.jackson.datatype.jsr310.deser.OneBasedMonthDeserializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rindholt.mealplanner.dailymealplaningredient.DailyMealPlanIngredient;
import rindholt.mealplanner.ingredient.Ingredient;

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
    private Set<DailyMealPlanIngredient> ingredients;

    public DailyMealPlan(LocalDate date) {
        this.date = date;
    }

    public DailyMealPlan(LocalDate date, Set<DailyMealPlanIngredient> ingredients) {
        this.date = date;
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