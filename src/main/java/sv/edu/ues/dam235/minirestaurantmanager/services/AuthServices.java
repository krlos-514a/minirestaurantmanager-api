package sv.edu.ues.dam235.minirestaurantmanager.services;

import sv.edu.ues.dam235.minirestaurantmanager.dtos.TokenDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.UserDTO;

import java.util.List;

public interface AuthServices {
    public TokenDTO login(String user, String pass);
    public UserDTO registerUser(UserDTO user);
    public void deleteById(Integer id);
    public Boolean existsById(Integer id);
    public UserDTO updateUser(UserDTO userDTO);
    public UserDTO getUser(Integer id);
    public Boolean findByEmail(String email);
    public List<UserDTO> getAllUsers();
}