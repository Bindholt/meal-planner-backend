package rindholt.mealplanner.ingredient;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ResponseEntity.ok(ingredientRepository.findAll());
    }

    public ResponseEntity<Ingredient> addIngredient(Ingredient ingredient) {
        return ResponseEntity.ok(ingredientRepository.save(ingredient));
    }

    public Optional<Ingredient> getIngredient(Long id) {
        return ingredientRepository.findById(id);
    }

    public ResponseEntity<Void> deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Ingredient> patchIngredient(Long id, Ingredient ingredient) {
        Optional<Ingredient> ingredientToUpdate = ingredientRepository.findById(id);

        ingredientToUpdate.ifPresent(updatedIngredient -> {
            if (ingredient.getName() != null) {
                updatedIngredient.setName(ingredient.getName());
            }
            if (ingredient.getUnit() != null) {
                updatedIngredient.setUnit(ingredient.getUnit());
            }
            ingredientRepository.save(updatedIngredient);
        });

        return ResponseEntity.of(ingredientToUpdate);
    }
}
