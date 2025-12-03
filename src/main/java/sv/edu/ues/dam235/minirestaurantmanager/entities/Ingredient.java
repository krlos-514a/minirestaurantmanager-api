package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "Ingredient")
public class Ingredient {
    @Id
    private String id;
    private String name;
    private String unitOfMeasure;
    private double quantity;
    private String ownerId;
    private boolean needsSync;
}