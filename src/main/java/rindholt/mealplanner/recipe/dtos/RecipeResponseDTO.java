package rindholt.mealplanner.recipe.dtos;

import rindholt.mealplanner.ingredientwithquantity.dtos.IngredientWithQuantityResponseDTO;

import java.util.Set;

public record RecipeResponseDTO(
        Long id,
        String title,
        String guide,
        Long minutes,
        String imageURL,
        Long portions,
        Set<IngredientWithQuantityResponseDTO> ingredients
) {
}
