package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasureDTO {
    private String name;
    private boolean isCustom;
    private String ownerId;
    private boolean needsSync;
}
