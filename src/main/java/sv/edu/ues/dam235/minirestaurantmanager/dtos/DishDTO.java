package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private String id;
    private String name;
    private double price;
    private String type;
    private String ownerId;
    private boolean needsSync;
}
