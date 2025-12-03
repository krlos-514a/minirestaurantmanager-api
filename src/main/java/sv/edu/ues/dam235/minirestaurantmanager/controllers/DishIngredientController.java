package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.DishIngredientDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.DishIngredientServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dishingredients")
public class DishIngredientController {
    final private DishIngredientServices dishIngredientServices;

    public DishIngredientController(DishIngredientServices dishIngredientServices) {
        this.dishIngredientServices = dishIngredientServices;
    }

    @GetMapping
    public ResponseEntity<List<DishIngredientDTO>> getAllDishIngredients() {
        try {
            List<DishIngredientDTO> items = dishIngredientServices.getAllDishIngredients();
            if (items.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            log.error("{}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/dish/{dishId}")
    public ResponseEntity<List<DishIngredientDTO>> getDishIngredientsByDish(@PathVariable String dishId) {
        try {
            List<DishIngredientDTO> items = dishIngredientServices.getDishIngredientsByDish(dishId);
            if (items.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            log.error("{}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DishIngredientDTO> createDishIngredient(@RequestBody DishIngredientDTO dto) {
        try {
            DishIngredientDTO newItem = dishIngredientServices.createDishIngredient(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            log.error("Error al crear DishIngredient: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{dishId}/{ingredientId}")
    public ResponseEntity<DishIngredientDTO> updateDishIngredient(@PathVariable String dishId, @PathVariable String ingredientId, @RequestBody DishIngredientDTO dto) {
        try {
            DishIngredientDTO updatedItem = dishIngredientServices.updateDishIngredient(dishId, ingredientId, dto);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            log.error("Error al actualizar DishIngredient: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar DishIngredient: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{dishId}/{ingredientId}")
    public ResponseEntity<Void> deleteDishIngredient(@PathVariable String dishId, @PathVariable String ingredientId) {
        try {
            dishIngredientServices.deleteDishIngredient(dishId, ingredientId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar DishIngredient: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar DishIngredient: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
