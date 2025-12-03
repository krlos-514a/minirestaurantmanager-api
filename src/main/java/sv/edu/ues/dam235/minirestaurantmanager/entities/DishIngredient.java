package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "DishIngredient")
public class DishIngredient {
    @Id
    private String dishId;
    private String ingredientId;
    private double quantityNeeded;
    private String ownerId;
    private boolean needsSync;
}