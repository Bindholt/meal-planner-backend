package rindholt.mealplanner.dailymealplan;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
}
