package rindholt.mealplanner.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantity;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantityRepository;
import rindholt.mealplanner.ingredient.Ingredient;
import rindholt.mealplanner.ingredient.IngredientRepository;
import rindholt.mealplanner.ingredient.IngredientService;
import rindholt.mealplanner.recipe.Recipe;
import rindholt.mealplanner.recipe.RecipeRepository;
import rindholt.mealplanner.recipe.RecipeService;

import java.util.*;

@Component
public class InitData implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;
    private final IngredientRepository ingredientRepository;
    private final IngredientService ingredientService;
    private final IngredientWithQuantityRepository ingredientWithQuantityRepository;

    public InitData(RecipeRepository recipeRepository, RecipeService recipeService, IngredientRepository ingredientRepository, IngredientService ingredientService, IngredientWithQuantityRepository ingredientWithQuantityRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
        this.ingredientRepository = ingredientRepository;
        this.ingredientService = ingredientService;
        this.ingredientWithQuantityRepository = ingredientWithQuantityRepository;
    }

    @Override
    public void run(String... args) {
        if (ingredientRepository.count() == 0) {
            addIngredients();
        }

        if (recipeRepository.count() == 0) {
            addRecipes();
        }
    }

    private void addIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredients.add(new Ingredient("Potato", "grams"));
        ingredients.add(new Ingredient("Carrot", "grams"));
        ingredients.add(new Ingredient("Onion", "grams"));
        ingredients.add(new Ingredient("Garlic", "grams"));
        ingredients.add(new Ingredient("Beef", "grams"));
        ingredients.add(new Ingredient("Chicken", "grams"));
        ingredients.add(new Ingredient("Pork", "grams"));
        ingredients.add(new Ingredient("Fish", "grams"));
        ingredients.add(new Ingredient("Shrimp", "grams"));
        ingredients.add(new Ingredient("Rice", "grams"));
        ingredients.add(new Ingredient("Pasta", "grams"));
        ingredients.add(new Ingredient("Tomato", "grams"));
        ingredients.add(new Ingredient("Pepper", "grams"));
        ingredients.add(new Ingredient("Salt", "grams"));
        ingredients.add(new Ingredient("Sugar", "grams"));
        ingredients.add(new Ingredient("Flour", "grams"));
        ingredients.add(new Ingredient("Egg", "pcs"));
        ingredients.add(new Ingredient("Milk", "ml"));
        ingredients.add(new Ingredient("Butter", "grams"));
        ingredients.add(new Ingredient("Cheese", "grams"));

        for (Ingredient ingredient : ingredients) {
            ingredientService.addIngredient(ingredient);
        }
    }

    private void addRecipes() {
        Set<IngredientWithQuantity> ingredientsForRecipe = new HashSet<>();

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            Optional<Ingredient> ingredientToAdd = ingredientRepository.findById((long) i);
            ingredientToAdd.ifPresent(ingredients::add);
        }

        for (Ingredient ingredient : ingredients) {
            IngredientWithQuantity ingredientWithQuantity = new IngredientWithQuantity(ingredient, 10L);
            ingredientWithQuantityRepository.save(ingredientWithQuantity);
            ingredientsForRecipe.add(ingredientWithQuantity);
        }

        String guide = "1. Peel the potatoes and carrots and cut them into small pieces.\n" +
                "2. Cut the onion and garlic into small pieces.\n" +
                "3. Fry the beef in a pan.\n" +
                "4. Add the onion and garlic and fry for a few minutes.\n" +
                "5. Add the potatoes and carrots and fry for a few minutes.\n" +
                "6. Add water and let it simmer for 30 minutes.\n" +
                "7. Add salt and pepper to taste.\n" +
                "8. Serve with rice.";

        Recipe recipe = new Recipe("Test Recipe", guide, 20L, "", 4L, ingredientsForRecipe);
        recipeRepository.save(recipe);
    }

}
