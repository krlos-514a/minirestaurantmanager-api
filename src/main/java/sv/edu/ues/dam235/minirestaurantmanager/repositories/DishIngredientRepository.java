package sv.edu.ues.dam235.minirestaurantmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.dam235.minirestaurantmanager.entities.DishIngredient;
import sv.edu.ues.dam235.minirestaurantmanager.entities.DishIngredientId;
import java.util.List;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, DishIngredientId> {
    List<DishIngredient> findByDishId(String dishId);
}
