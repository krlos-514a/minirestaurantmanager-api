package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.IngredientDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.UserDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.IngredientServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/ingredients")
public class IngredientController {
    final private IngredientServices ingredientServices;
    private IngredientController(IngredientServices ingredientServices) {
        this.ingredientServices = ingredientServices;
    }
    @GetMapping
    public ResponseEntity<List<IngredientDTO>> getAllIngredientes() {
        try {
            List<IngredientDTO> items = ingredientServices.getAllIngredients();
            if (items.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<IngredientDTO> getIngredient(@PathVariable String id) {
        try {
            IngredientDTO ingredient = ingredientServices.getIngredient(id);
            if (ingredient == null) {
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.ok(ingredient);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }
    @PostMapping
    public ResponseEntity<IngredientDTO> createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        try {
            IngredientDTO newIngredient = ingredientServices.createIngredient(ingredientDTO);
            log.info("Error al insertar el ingrediente: " + ingredientDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(newIngredient);
        } catch (Exception e) {
            log.error("Error al crear el ingrediente: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDTO> updateIngredient(@PathVariable String id, @RequestBody IngredientDTO ingredientDTO) {
        try {
            IngredientDTO updatedIngredient = ingredientServices.updateIngredient(id, ingredientDTO);
            return ResponseEntity.ok(updatedIngredient);
        } catch (RuntimeException e) {
            log.error("Error al actualizar el ingrediente: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar el ingrediente: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable String id) {
        try {
            ingredientServices.deleteIngredient(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar el ingrediente: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar el ingrediente: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
