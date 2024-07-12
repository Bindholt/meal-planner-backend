package rindholt.mealplanner.dailymealplan;

import org.springframework.stereotype.Service;

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


}
