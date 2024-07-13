package rindholt.mealplanner.dailymealplan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rindholt.mealplanner.dailymealplaningredient.DailyMealPlanIngredient;
import rindholt.mealplanner.ingredient.Ingredient;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/dailymealplan")
public class DailyMealPlanController {
    private final DailyMealPlanService dailyMealPlanService;

    public DailyMealPlanController(DailyMealPlanService dailyMealPlanService) {
        this.dailyMealPlanService = dailyMealPlanService;
    }
    @GetMapping("/{date}")
    public ResponseEntity<DailyMealPlan> getDailyMealPlan(@PathVariable LocalDate date) {
        return ResponseEntity.of(dailyMealPlanService.getDailyMealPlan(date));
    }

    @PostMapping
    public ResponseEntity<DailyMealPlan> addDailyMealPlan(@RequestBody DailyMealPlan dailyMealPlan) {
        return dailyMealPlanService.addDailyMealPlan(dailyMealPlan);
    }

    @PostMapping("/{id}/addIngredient")
    public ResponseEntity<DailyMealPlan> addIngredient(@PathVariable Long id, @RequestBody DailyMealPlanIngredient ingredient) {
        return dailyMealPlanService.addIngredient(id, ingredient);
    }

    @DeleteMapping("/{id}/deleteIngredient")
    public ResponseEntity<DailyMealPlan> deleteIngredient(@PathVariable Long id, @RequestBody DailyMealPlanIngredient ingredient) {
        return dailyMealPlanService.deleteIngredient(id, ingredient);
    }

}