package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.OrderDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.OrderServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {
    final private OrderServices orderServices;

    public OrderController(OrderServices orderServices) {
        this.orderServices = orderServices;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            List<OrderDTO> items = orderServices.getAllOrders();
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
    public ResponseEntity<OrderDTO> getOrder(@PathVariable String id) {
        try {
            OrderDTO item = orderServices.getOrder(id);
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
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto) {
        try {
            OrderDTO newItem = orderServices.createOrder(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            log.error("Error al crear el pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable String id, @RequestBody OrderDTO dto) {
        try {
            OrderDTO updatedItem = orderServices.updateOrder(id, dto);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            log.error("Error al actualizar el pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar el pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        try {
            orderServices.deleteOrder(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar el pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar el pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
