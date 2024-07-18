package rindholt.mealplanner.dailymealplan;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import rindholt.mealplanner.ingredientwithquantity.IngredientWithQuantity;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DailyMealPlanService {
    private final DailyMealPlanRepository dailyMealPlanRepository;

    public DailyMealPlanService(DailyMealPlanRepository dailyMealPlanRepository) {
        this.dailyMealPlanRepository = dailyMealPlanRepository;
    }

    public Optional<DailyMealPlan> getDailyMealPlan(LocalDate date) {
        return dailyMealPlanRepository.findByDate(date);
    }


    public ResponseEntity<DailyMealPlan> addDailyMealPlan(DailyMealPlan dailyMealPlan) {
        return ResponseEntity.ok(dailyMealPlanRepository.save(dailyMealPlan));
    }

    public ResponseEntity<DailyMealPlan> addIngredient(Long id, IngredientWithQuantity ingredient) {
        Optional<DailyMealPlan> existingDailyMealPlan = dailyMealPlanRepository.findById(id);

        existingDailyMealPlan.ifPresent(dailyMealPlan -> {
            dailyMealPlan.addIngredient(ingredient);
            dailyMealPlanRepository.save(dailyMealPlan);
        });
        return ResponseEntity.of(existingDailyMealPlan);
    }

    public ResponseEntity<DailyMealPlan> deleteIngredient(Long id, IngredientWithQuantity ingredient) {
        Optional<DailyMealPlan> existingDailyMealPlan = dailyMealPlanRepository.findById(id);

        existingDailyMealPlan.ifPresent(dailyMealPlan -> {
            dailyMealPlan.removeIngredient(ingredient);
            dailyMealPlanRepository.save(dailyMealPlan);
        });
        return ResponseEntity.of(existingDailyMealPlan);
    }
}
