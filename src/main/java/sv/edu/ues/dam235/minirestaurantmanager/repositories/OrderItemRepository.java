package sv.edu.ues.dam235.minirestaurantmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.dam235.minirestaurantmanager.entities.OrderItem;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    List<OrderItem> findByOrderId(String orderId);
}
