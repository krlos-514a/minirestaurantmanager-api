package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.DishIngredientDTO;
import java.util.List;

public interface DishIngredientServices {

    List<DishIngredientDTO> getAllDishIngredients();

    DishIngredientDTO getDishIngredient(String dishId, String ingredientId);

    List<DishIngredientDTO> getDishIngredientsByDish(String dishId);

    DishIngredientDTO createDishIngredient(DishIngredientDTO dishIngredientDTO);

    DishIngredientDTO updateDishIngredient(String dishId, String ingredientId, DishIngredientDTO dishIngredientDTO);

    void deleteDishIngredient(String dishId, String ingredientId);
}
