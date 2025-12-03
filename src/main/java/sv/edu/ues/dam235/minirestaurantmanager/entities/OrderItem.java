package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "OrderItem")
public class OrderItem {
    @Id
    private String id;
    private String orderId;
    private String dishId;
    private int quantity;
    private double priceAtTime;
    private String dishNameSnapshot;
    private String ownerId;
    private boolean needsSync;
}