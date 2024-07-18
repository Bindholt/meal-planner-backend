package rindholt.mealplanner.recipe.dtos;

import rindholt.mealplanner.ingredientwithquantity.dtos.IngredientWithQuantityRequestDTO;

import java.util.Set;

public record RecipeRequestDTO(
        Long id,
        String title,
        String guide,
        Long minutes,
        String imageURL,
        Long portions,
        Set<IngredientWithQuantityRequestDTO> ingredients
) {
}
