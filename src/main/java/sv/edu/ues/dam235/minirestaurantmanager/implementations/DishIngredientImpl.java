package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.DishIngredientDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.DishIngredient;
import sv.edu.ues.dam235.minirestaurantmanager.entities.DishIngredientId;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.DishIngredientRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.DishIngredientServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DishIngredientImpl implements DishIngredientServices {
    private final DishIngredientRepository dishIngredientRepository;

    public DishIngredientImpl(DishIngredientRepository dishIngredientRepository) {
        this.dishIngredientRepository = dishIngredientRepository;
    }

    @Override
    public List<DishIngredientDTO> getAllDishIngredients() {
        List<DishIngredientDTO> result = new ArrayList<>();
        List<DishIngredient> items = this.dishIngredientRepository.findAll();
        for (DishIngredient item : items) {
            result.add(new DishIngredientDTO(item.getDishId(), item.getIngredientId(), item.getQuantityNeeded(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public DishIngredientDTO getDishIngredient(String dishId, String ingredientId) {
        DishIngredientId id = new DishIngredientId(dishId, ingredientId);
        Optional<DishIngredient> result = this.dishIngredientRepository.findById(id);
        if (result.isPresent()) {
            DishIngredient item = result.get();
            return new DishIngredientDTO(item.getDishId(), item.getIngredientId(), item.getQuantityNeeded(), item.getOwnerId(), item.isNeedsSync());
        }
        return null;
    }

    @Override
    public List<DishIngredientDTO> getDishIngredientsByDish(String dishId) {
        List<DishIngredientDTO> result = new ArrayList<>();
        List<DishIngredient> items = this.dishIngredientRepository.findByDishId(dishId);
        for (DishIngredient item : items) {
            result.add(new DishIngredientDTO(item.getDishId(), item.getIngredientId(), item.getQuantityNeeded(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public DishIngredientDTO createDishIngredient(DishIngredientDTO dto) {
        DishIngredient item = new DishIngredient();
        item.setDishId(dto.getDishId());
        item.setIngredientId(dto.getIngredientId());
        item.setQuantityNeeded(dto.getQuantityNeeded());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = dishIngredientRepository.save(item);
        return new DishIngredientDTO(item.getDishId(), item.getIngredientId(), item.getQuantityNeeded(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public DishIngredientDTO updateDishIngredient(String dishId, String ingredientId, DishIngredientDTO dto) {
        DishIngredientId id = new DishIngredientId(dishId, ingredientId);
        DishIngredient item = dishIngredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DishIngredient not found"));

        item.setQuantityNeeded(dto.getQuantityNeeded());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = dishIngredientRepository.save(item);
        return new DishIngredientDTO(item.getDishId(), item.getIngredientId(), item.getQuantityNeeded(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public void deleteDishIngredient(String dishId, String ingredientId) {
        DishIngredientId id = new DishIngredientId(dishId, ingredientId);
        if (!dishIngredientRepository.existsById(id)) {
            throw new RuntimeException("DishIngredient not found");
        }
        dishIngredientRepository.deleteById(id);
    }
}
