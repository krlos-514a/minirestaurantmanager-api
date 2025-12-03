package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private String id;
    private String clientName;
    private String status;
    private double total;
    private long timestamp;
    private String ownerId;
    private boolean needsSync;
}
