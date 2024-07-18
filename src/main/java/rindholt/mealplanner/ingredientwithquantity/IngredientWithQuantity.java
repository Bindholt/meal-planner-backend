package rindholt.mealplanner.ingredientwithquantity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rindholt.mealplanner.ingredient.Ingredient;

@Entity //får en tabel i databasen
@NoArgsConstructor //laver en tom constructor
@Getter //laver getters
@Setter //laver setters
public class IngredientWithQuantity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Ingredient ingredient;
    private Long quantity;

    public IngredientWithQuantity(Ingredient ingredient, Long quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }
}