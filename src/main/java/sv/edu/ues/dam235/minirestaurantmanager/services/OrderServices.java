package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.OrderDTO;
import java.util.List;

public interface OrderServices {

    List<OrderDTO> getAllOrders();

    OrderDTO getOrder(String id);

    OrderDTO createOrder(OrderDTO orderDTO);

    OrderDTO updateOrder(String id, OrderDTO orderDTO);

    void deleteOrder(String id);
}
