package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.DishDTO;
import java.util.List;

public interface DishServices {

    List<DishDTO> getAllDishes();

    DishDTO getDish(String id);

    DishDTO createDish(DishDTO dishDTO);

    DishDTO updateDish(String id, DishDTO dishDTO);

    void deleteDish(String id);
}
