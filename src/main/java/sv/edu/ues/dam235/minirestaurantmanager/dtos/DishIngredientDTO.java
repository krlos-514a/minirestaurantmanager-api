package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishIngredientDTO {
    private String dishId;
    private String ingredientId;
    private double quantityNeeded;
    private String ownerId;
    private boolean needsSync;
}
