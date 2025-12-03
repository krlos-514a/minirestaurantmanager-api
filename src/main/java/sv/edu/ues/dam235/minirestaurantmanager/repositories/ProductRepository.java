package sv.edu.ues.dam235.minirestaurantmanager.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}