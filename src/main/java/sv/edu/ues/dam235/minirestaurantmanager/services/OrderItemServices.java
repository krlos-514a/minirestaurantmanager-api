package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.OrderItemDTO;
import java.util.List;

public interface OrderItemServices {

    List<OrderItemDTO> getAllOrderItems();

    OrderItemDTO getOrderItem(String id);

    List<OrderItemDTO> getOrderItemsByOrder(String orderId);

    OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO);

    OrderItemDTO updateOrderItem(String id, OrderItemDTO orderItemDTO);

    void deleteOrderItem(String id);
}
