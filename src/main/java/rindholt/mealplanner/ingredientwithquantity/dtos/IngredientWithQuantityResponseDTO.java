package rindholt.mealplanner.ingredientwithquantity.dtos;

public record IngredientWithQuantityResponseDTO(
        Long ingredientId,
        String name,
        String unit,
        Long quantity
) {
}

