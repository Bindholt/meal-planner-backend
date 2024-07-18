package rindholt.mealplanner.recipe;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantity;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantityRepository;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantityService;
import rindholt.mealplanner.ingredientwithquantity.dtos.IngredientWithQuantityResponseDTO;
import rindholt.mealplanner.recipe.dtos.RecipeRequestDTO;
import rindholt.mealplanner.recipe.dtos.RecipeResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final IngredientWithQuantityService ingredientWithQuantityService;
    private final IngredientWithQuantityRepository ingredientWithQuantityRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientWithQuantityService ingredientWithQuantityService, IngredientWithQuantityRepository ingredientWithQuantityRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientWithQuantityService = ingredientWithQuantityService;
        this.ingredientWithQuantityRepository = ingredientWithQuantityRepository;
    }

    public ResponseEntity<List<RecipeResponseDTO>> getRecipes() {
        return ResponseEntity.ok(
                recipeRepository.findAll().stream()
                        .map(this::toDTO)
                        .collect(Collectors.toList())
        );
    }

    public Optional<RecipeResponseDTO> getRecipe(Long id) {
        return recipeRepository.findById(id)
                .map(this::toDTO);
    }

    public ResponseEntity<RecipeResponseDTO> addRecipe(RecipeRequestDTO recipeDto) {
        Recipe recipe = fromDTO(recipeDto);

        Set<IngredientWithQuantity> savedIngredients = saveIngredientsIfNeeded(recipe.getIngredients());
        recipe.setIngredients(savedIngredients);
        Recipe savedRecipe = recipeRepository.save(recipe);
        RecipeResponseDTO responseDTO = toDTO(savedRecipe);

        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<Void> deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<RecipeResponseDTO> updateRecipe(RecipeRequestDTO recipe) {
        Optional<Recipe> recipeToUpdate = recipeRepository.findById(recipe.id());

        recipeToUpdate.ifPresent(existingRecipeToUpdate -> {
            existingRecipeToUpdate.setTitle(recipe.title());
            existingRecipeToUpdate.setGuide(recipe.guide());
            existingRecipeToUpdate.setPortions(recipe.portions());
            existingRecipeToUpdate.setImageURL(recipe.imageURL());
            existingRecipeToUpdate.setMinutes(recipe.minutes());
            existingRecipeToUpdate.setIngredients(recipe.ingredients().stream().map(ingredientWithQuantityService::fromDTO).collect(Collectors.toSet()));

            Set<IngredientWithQuantity> savedIngredients = saveIngredientsIfNeeded(existingRecipeToUpdate.getIngredients());
            existingRecipeToUpdate.setIngredients(savedIngredients);

            recipeRepository.save(existingRecipeToUpdate);
        });

        return ResponseEntity.of(recipeToUpdate.map(this::toDTO));
    }

    private Set<IngredientWithQuantity> saveIngredientsIfNeeded(Set<IngredientWithQuantity> ingredients) {
        return ingredients.stream()
                .map(ingredientWithQuantity -> {
                    // If ingredientWithQuantity has no ID, it means it's new and needs saving
                    if (ingredientWithQuantity.getId() == null) {
                        return ingredientWithQuantityRepository.save(ingredientWithQuantity);
                    }
                    return ingredientWithQuantity;
                })
                .collect(Collectors.toSet());
    }

    private RecipeResponseDTO toDTO(Recipe recipe) {
        Set<IngredientWithQuantityResponseDTO> ingredientDTOs = recipe.getIngredients().stream()
                .map(ingredientWithQuantityService::toDTO)
                .collect(Collectors.toSet());

        return new RecipeResponseDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getGuide(),
                recipe.getMinutes(),
                recipe.getImageURL(),
                recipe.getPortions(),
                ingredientDTOs
        );
    }

    private Recipe fromDTO(RecipeRequestDTO recipe) {
        return new Recipe(
                recipe.title(),
                recipe.guide(),
                recipe.minutes(),
                recipe.imageURL(),
                recipe.portions(),
                recipe.ingredients().stream()
                        .map(ingredientWithQuantityService::fromDTO)
                        .collect(Collectors.toSet())
        );
    }
}
