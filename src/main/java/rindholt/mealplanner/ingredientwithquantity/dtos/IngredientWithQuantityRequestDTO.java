package rindholt.mealplanner.ingredientwithquantity.dtos;

public record IngredientWithQuantityRequestDTO(
        Long ingredientId,
        String name,
        String unit,
        Long quantity
) {
}
