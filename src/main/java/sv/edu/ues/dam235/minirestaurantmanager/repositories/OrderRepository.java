package sv.edu.ues.dam235.minirestaurantmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
