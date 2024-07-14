package rindholt.mealplanner.recipe;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public ResponseEntity<List<Recipe>> getRecipes() {
        return ResponseEntity.ok(recipeRepository.findAll());
    }

    public Optional<Recipe> getRecipe(Long id) {
        return recipeRepository.findById(id);
    }

    public ResponseEntity<Recipe> addRecipe(Recipe recipe) {
        return ResponseEntity.ok(recipeRepository.save(recipe));
    }

    public ResponseEntity<Void> deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Recipe> updateRecipe(Recipe recipe) {
        Optional<Recipe> recipeToUpdate = recipeRepository.findById(recipe.getId());

        recipeToUpdate.ifPresent(existingRecipeToUpdate -> {
            existingRecipeToUpdate.setTitle(recipe.getTitle());
            existingRecipeToUpdate.setProcedure(recipe.getProcedure());
            existingRecipeToUpdate.setPortions(recipe.getPortions());
            existingRecipeToUpdate.setImageURL(recipe.getImageURL());
            existingRecipeToUpdate.setMinutes(recipe.getMinutes());
            existingRecipeToUpdate.setIngredients(recipe.getIngredients());
            recipeRepository.save(existingRecipeToUpdate);
        });

        return ResponseEntity.of(recipeToUpdate);
    }
}
