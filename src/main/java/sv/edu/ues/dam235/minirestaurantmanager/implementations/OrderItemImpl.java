package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.OrderItemDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.OrderItem;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.OrderItemRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.OrderItemServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderItemImpl implements OrderItemServices {
    private final OrderItemRepository orderItemRepository;

    public OrderItemImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItemDTO> result = new ArrayList<>();
        List<OrderItem> items = this.orderItemRepository.findAll();
        for (OrderItem item : items) {
            result.add(new OrderItemDTO(item.getId(), item.getOrderId(), item.getDishId(), item.getQuantity(), item.getPriceAtTime(), item.getDishNameSnapshot(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public OrderItemDTO getOrderItem(String id) {
        Optional<OrderItem> result = this.orderItemRepository.findById(id);
        if (result.isPresent()) {
            OrderItem item = result.get();
            return new OrderItemDTO(item.getId(), item.getOrderId(), item.getDishId(), item.getQuantity(), item.getPriceAtTime(), item.getDishNameSnapshot(), item.getOwnerId(), item.isNeedsSync());
        }
        return null;
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrder(String orderId) {
        List<OrderItemDTO> result = new ArrayList<>();
        List<OrderItem> items = this.orderItemRepository.findByOrderId(orderId);
        for (OrderItem item : items) {
            result.add(new OrderItemDTO(item.getId(), item.getOrderId(), item.getDishId(), item.getQuantity(), item.getPriceAtTime(), item.getDishNameSnapshot(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public OrderItemDTO createOrderItem(OrderItemDTO dto) {
        OrderItem item = new OrderItem();
        item.setId(dto.getId());
        item.setOrderId(dto.getOrderId());
        item.setDishId(dto.getDishId());
        item.setQuantity(dto.getQuantity());
        item.setPriceAtTime(dto.getPriceAtTime());
        item.setDishNameSnapshot(dto.getDishNameSnapshot());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = orderItemRepository.save(item);
        return new OrderItemDTO(item.getId(), item.getOrderId(), item.getDishId(), item.getQuantity(), item.getPriceAtTime(), item.getDishNameSnapshot(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public OrderItemDTO updateOrderItem(String id, OrderItemDTO dto) {
        OrderItem item = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));
        item.setOrderId(dto.getOrderId());
        item.setDishId(dto.getDishId());
        item.setQuantity(dto.getQuantity());
        item.setPriceAtTime(dto.getPriceAtTime());
        item.setDishNameSnapshot(dto.getDishNameSnapshot());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = orderItemRepository.save(item);
        return new OrderItemDTO(item.getId(), item.getOrderId(), item.getDishId(), item.getQuantity(), item.getPriceAtTime(), item.getDishNameSnapshot(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public void deleteOrderItem(String id) {
        if (!orderItemRepository.existsById(id)) {
            throw new RuntimeException("OrderItem not found");
        }
        orderItemRepository.deleteById(id);
    }
}
