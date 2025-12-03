package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "UnitOfMeasure")
public class UnitOfMeasure {
    @Id
    private String name;
    private boolean isCustom;
    private String ownerId;
    private boolean needsSync;
}