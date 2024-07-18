package rindholt.mealplanner.recipe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rindholt.mealplanner.recipe.dtos.RecipeRequestDTO;
import rindholt.mealplanner.recipe.dtos.RecipeResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponseDTO>> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDTO> getRecipe(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getRecipe(id));
    }

    @PostMapping
    public ResponseEntity<RecipeResponseDTO> addRecipe(@RequestBody RecipeRequestDTO recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PutMapping
    public ResponseEntity<RecipeResponseDTO> updateRecipe(@RequestBody RecipeRequestDTO recipe) {
        return recipeService.updateRecipe(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        return recipeService.deleteRecipe(id);
    }

}
