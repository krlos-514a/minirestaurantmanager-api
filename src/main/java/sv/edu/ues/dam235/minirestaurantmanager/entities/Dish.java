package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Dish")
public class Dish {
    @Id
    private String id;
    private String name;
    private double price;
    private String type;
    private String ownerId;
    private boolean needsSync;
}