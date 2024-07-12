package rindholt.mealplanner.dailymealplan;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface DailyMealPlanRepository extends JpaRepository<DailyMealPlan, Long> {
    Optional<DailyMealPlan> findByDate(LocalDate date);
}
