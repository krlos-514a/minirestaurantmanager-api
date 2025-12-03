package sv.edu.ues.dam235.minirestaurantmanager.entities;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "[User]")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fullName;
    private String email;
    private String password;
    private String role;
    private String ownerId;

}