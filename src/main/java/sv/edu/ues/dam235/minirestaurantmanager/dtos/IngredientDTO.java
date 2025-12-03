package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private String id;
    private String name;
    private String unitOfMeasure;
    private double quantity;
    private String ownerId;
    private boolean needsSync;
}