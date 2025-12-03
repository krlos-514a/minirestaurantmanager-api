package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.OrderDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Order;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.OrderRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.OrderServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderImpl implements OrderServices {
    private final OrderRepository orderRepository;

    public OrderImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderDTO> result = new ArrayList<>();
        List<Order> items = this.orderRepository.findAll();
        for (Order item : items) {
            result.add(new OrderDTO(item.getId(), item.getClientName(), item.getStatus(), item.getTotal(), item.getTimestamp(), item.getOwnerId(), item.isNeedsSync()));
        }
        return result;
    }

    @Override
    public OrderDTO getOrder(String id) {
        Optional<Order> result = this.orderRepository.findById(id);
        if (result.isPresent()) {
            Order item = result.get();
            return new OrderDTO(item.getId(), item.getClientName(), item.getStatus(), item.getTotal(), item.getTimestamp(), item.getOwnerId(), item.isNeedsSync());
        }
        return null;
    }

    @Override
    public OrderDTO createOrder(OrderDTO dto) {
        Order item = new Order();
        item.setId(dto.getId());
        item.setClientName(dto.getClientName());
        item.setStatus(dto.getStatus());
        item.setTotal(dto.getTotal());
        item.setTimestamp(dto.getTimestamp());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = orderRepository.save(item);
        return new OrderDTO(item.getId(), item.getClientName(), item.getStatus(), item.getTotal(), item.getTimestamp(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public OrderDTO updateOrder(String id, OrderDTO dto) {
        Order item = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        item.setClientName(dto.getClientName());
        item.setStatus(dto.getStatus());
        item.setTotal(dto.getTotal());
        item.setTimestamp(dto.getTimestamp());
        item.setOwnerId(dto.getOwnerId());
        item.setNeedsSync(dto.isNeedsSync());
        item = orderRepository.save(item);
        return new OrderDTO(item.getId(), item.getClientName(), item.getStatus(), item.getTotal(), item.getTimestamp(), item.getOwnerId(), item.isNeedsSync());
    }

    @Override
    public void deleteOrder(String id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found");
        }
        orderRepository.deleteById(id);
    }
}
