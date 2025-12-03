package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.OrderItemDTO;
import sv.edu.ues.dam235.minirestaurantmanager.services.OrderItemServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orderitems")
public class OrderItemController {
    final private OrderItemServices orderItemServices;

    public OrderItemController(OrderItemServices orderItemServices) {
        this.orderItemServices = orderItemServices;
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        try {
            List<OrderItemDTO> items = orderItemServices.getAllOrderItems();
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

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderItemDTO>> getOrderItemsByOrder(@PathVariable String orderId) {
        try {
            List<OrderItemDTO> items = orderItemServices.getOrderItemsByOrder(orderId);
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
    public ResponseEntity<OrderItemDTO> getOrderItem(@PathVariable String id) {
        try {
            OrderItemDTO item = orderItemServices.getOrderItem(id);
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
    public ResponseEntity<OrderItemDTO> createOrderItem(@RequestBody OrderItemDTO dto) {
        try {
            OrderItemDTO newItem = orderItemServices.createOrderItem(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newItem);
        } catch (Exception e) {
            log.error("Error al crear el item del pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItemDTO> updateOrderItem(@PathVariable String id, @RequestBody OrderItemDTO dto) {
        try {
            OrderItemDTO updatedItem = orderItemServices.updateOrderItem(id, dto);
            return ResponseEntity.ok(updatedItem);
        } catch (RuntimeException e) {
            log.error("Error al actualizar el item del pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al actualizar el item del pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable String id) {
        try {
            orderItemServices.deleteOrderItem(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            log.error("Error al eliminar el item del pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            log.error("Error al eliminar el item del pedido: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
