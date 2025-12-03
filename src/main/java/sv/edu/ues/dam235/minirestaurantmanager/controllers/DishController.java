package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.DishDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.DishServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dishes")
public class DishController {
    final private DishServices dishServices;

    public DishController(DishServices dishServices) {
        this.dishServices = dishServices;
    }

    @GetMapping
    public ResponseEntity<List<DishDTO>> getAllDishes() {
        try {
            List<DishDTO> items = dishServices.getAllDishes();
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

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> getDish(@PathVariable String id) {
        try {
            DishDTO item = dishServices.getDish(id);
            if (item == null) {
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.ok(item);
            }
        } catch (Exception e) {
            log.error("{}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<DishDTO> createDish(@RequestBody DishDTO dishDTO) {
        try {
            DishDTO newItem = dishServices.createDish(dishDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            log.error("Error al crear el plato: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> updateDish(@PathVariable String id, @RequestBody DishDTO dishDTO) {
        try {
            DishDTO updatedItem = dishServices.updateDish(id, dishDTO);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            log.error("Error al actualizar el plato: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar el plato: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDish(@PathVariable String id) {
        try {
            dishServices.deleteDish(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar el plato: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar el plato: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
