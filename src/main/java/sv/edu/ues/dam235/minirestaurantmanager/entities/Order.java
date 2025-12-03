package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "RestaurantOrder")
public class Order {
    @Id
    private String id;
    private String clientName;
    private String status;
    private double total;
    private long timestamp;
    private String ownerId;
    private boolean needsSync;
}