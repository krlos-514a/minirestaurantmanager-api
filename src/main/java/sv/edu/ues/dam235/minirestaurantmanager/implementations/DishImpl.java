package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.DishDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Dish;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.DishRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.DishServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DishImpl implements DishServices {
    private final DishRepository dishRepository;

    public DishImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public List<DishDTO> getAllDishes() {
        List<DishDTO> result = new ArrayList<>();
        List<Dish> items = this.dishRepository.findAll();
        for (Dish item : items) {
            result.add(new DishDTO(item.getId(), item.getName(), item.getPrice(), item.getType(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public DishDTO getDish(String id) {
        Optional<Dish> result = this.dishRepository.findById(id);
        if (result.isPresent()) {
            Dish dish = result.get();
            return new DishDTO(dish.getId(), dish.getName(), dish.getPrice(), dish.getType(), dish.getOwnerId(), dish.isNeedsSync());
        }
        return null;
    }

    @Override
    public DishDTO createDish(DishDTO dishDTO) {
        Dish dish = new Dish();
        dish.setId(dishDTO.getId());
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        dish.setType(dishDTO.getType());
        dish.setOwnerId(dishDTO.getOwnerId());
        dish.setNeedsSync(dishDTO.isNeedsSync());
        dish = dishRepository.save(dish);
        return new DishDTO(dish.getId(), dish.getName(), dish.getPrice(), dish.getType(), dish.getOwnerId(), dish.isNeedsSync());
    }

    @Override
    public DishDTO updateDish(String id, DishDTO dishDTO) {
        Dish dish = dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        dish.setType(dishDTO.getType());
        dish.setOwnerId(dishDTO.getOwnerId());
        dish.setNeedsSync(dishDTO.isNeedsSync());
        dish = dishRepository.save(dish);
        return new DishDTO(dish.getId(), dish.getName(), dish.getPrice(), dish.getType(), dish.getOwnerId(), dish.isNeedsSync());
    }

    @Override
    public void deleteDish(String id) {
        if (!dishRepository.existsById(id)) {
            throw new RuntimeException("Dish not found");
        }
        dishRepository.deleteById(id);
    }
}
