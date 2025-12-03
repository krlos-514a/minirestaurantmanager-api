package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.UnitOfMeasureDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.UnitOfMeasureServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/unitsofmeasure")
public class UnitOfMeasureController {
    final private UnitOfMeasureServices unitOfMeasureServices;

    public UnitOfMeasureController(UnitOfMeasureServices unitOfMeasureServices) {
        this.unitOfMeasureServices = unitOfMeasureServices;
    }

    @GetMapping
    public ResponseEntity<List<UnitOfMeasureDTO>> getAllUnitsOfMeasure() {
        try {
            List<UnitOfMeasureDTO> items = unitOfMeasureServices.getAllUnitsOfMeasure();
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

    @GetMapping("/{name}")
    public ResponseEntity<UnitOfMeasureDTO> getUnitOfMeasure(@PathVariable String name) {
        try {
            UnitOfMeasureDTO item = unitOfMeasureServices.getUnitOfMeasure(name);
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
    public ResponseEntity<UnitOfMeasureDTO> createUnitOfMeasure(@RequestBody UnitOfMeasureDTO dto) {
        try {
            UnitOfMeasureDTO newItem = unitOfMeasureServices.createUnitOfMeasure(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            log.error("Error al crear la unidad de medida: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{name}")
    public ResponseEntity<UnitOfMeasureDTO> updateUnitOfMeasure(@PathVariable String name, @RequestBody UnitOfMeasureDTO dto) {
        try {
            UnitOfMeasureDTO updatedItem = unitOfMeasureServices.updateUnitOfMeasure(name, dto);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            log.error("Error al actualizar la unidad de medida: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar la unidad de medida: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteUnitOfMeasure(@PathVariable String name) {
        try {
            unitOfMeasureServices.deleteUnitOfMeasure(name);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar la unidad de medida: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar la unidad de medida: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
