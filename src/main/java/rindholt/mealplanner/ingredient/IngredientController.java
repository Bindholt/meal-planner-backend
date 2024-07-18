package rindholt.mealplanner.ingredient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients() {
        return ingredientService.getIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getIngredient(id));
    }

    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        return ingredientService.deleteIngredient(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ingredient> patchIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ingredientService.patchIngredient(id, ingredient);
    }
}
