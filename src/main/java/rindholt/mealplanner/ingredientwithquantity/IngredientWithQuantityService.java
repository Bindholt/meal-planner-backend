package rindholt.mealplanner.ingredientwithquantity;

import org.springframework.stereotype.Service;
import rindholt.mealplanner.ingredient.Ingredient;
import rindholt.mealplanner.ingredient.IngredientRepository;
import rindholt.mealplanner.ingredientwithquantity.dtos.IngredientWithQuantityRequestDTO;
import rindholt.mealplanner.ingredientwithquantity.dtos.IngredientWithQuantityResponseDTO;
import rindholt.mealplanner.recipe.Recipe;
import rindholt.mealplanner.recipe.dtos.RecipeRequestDTO;

import java.util.Optional;

@Service
public class IngredientWithQuantityService {
    private final IngredientWithQuantityRepository ingredientWithQuantityRepository;
    private final IngredientRepository ingredientRepository;

    public IngredientWithQuantityService(IngredientWithQuantityRepository ingredientWithQuantityRepository, IngredientRepository ingredientRepository) {
        this.ingredientWithQuantityRepository = ingredientWithQuantityRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public IngredientWithQuantityResponseDTO toDTO(IngredientWithQuantity ingredientWithQuantity) {
        return new IngredientWithQuantityResponseDTO(
                ingredientWithQuantity.getIngredient().getId(),
                ingredientWithQuantity.getIngredient().getName(),
                ingredientWithQuantity.getIngredient().getUnit(),
                ingredientWithQuantity.getQuantity()
        );
    }

    public IngredientWithQuantity fromDTO(IngredientWithQuantityRequestDTO dto) {
        Optional<IngredientWithQuantity> ingredientWithQuantityOpt = ingredientWithQuantityRepository.findByIngredientIdAndQuantity(dto.ingredientId(), dto.quantity());

        if(ingredientWithQuantityOpt.isPresent()) {
            return ingredientWithQuantityOpt.get();
        }

        Optional<Ingredient> ingredient = ingredientRepository.findById(dto.ingredientId());
        if(ingredient.isEmpty()) {
            throw new IllegalArgumentException("Ingredient not found");
        }

        return new IngredientWithQuantity(ingredient.get(), dto.quantity());
    }
}
