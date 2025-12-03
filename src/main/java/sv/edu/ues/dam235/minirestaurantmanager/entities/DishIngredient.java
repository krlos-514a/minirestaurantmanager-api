package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "DishIngredient")
@IdClass(DishIngredientId.class)
public class DishIngredient {
    @Id
    private String dishId;
    @Id
    private String ingredientId;
    private double quantityNeeded;
    private String ownerId;
    private boolean needsSync;
}
