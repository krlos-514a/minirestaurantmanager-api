package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private String id;
    private String orderId;
    private String dishId;
    private int quantity;
    private double priceAtTime;
    private String dishNameSnapshot;
    private String ownerId;
    private boolean needsSync;
}
