package sv.edu.ues.dam235.minirestaurantmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}