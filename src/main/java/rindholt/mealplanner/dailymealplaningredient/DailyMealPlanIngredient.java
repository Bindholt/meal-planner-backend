package rindholt.mealplanner.dailymealplaningredient;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rindholt.mealplanner.ingredient.Ingredient;

@Entity //får en tabel i databasen
@NoArgsConstructor //laver en tom constructor
@Getter //laver getters
@Setter //laver setters
public class DailyMealPlanIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Ingredient ingredient;
    private Long quantity;

    public DailyMealPlanIngredient(Ingredient ingredient, Long quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}