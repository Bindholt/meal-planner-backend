package rindholt.mealplanner.ingredientwithquantity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientWithQuantityRepository extends JpaRepository<IngredientWithQuantity, Long> {
    Optional<IngredientWithQuantity> findByIngredientIdAndQuantity(Long Id, Long quantity);
}
