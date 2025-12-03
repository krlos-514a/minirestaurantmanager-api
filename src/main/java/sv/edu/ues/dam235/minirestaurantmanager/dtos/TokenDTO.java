package sv.edu.ues.dam235.minirestaurantmanager.dtos;

import lombok.Data;
@Data
public class TokenDTO {
    private String token;
    private String expireIn;
    private String msj;
}