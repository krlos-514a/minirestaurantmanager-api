package sv.edu.ues.dam235.minirestaurantmanager.implementations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.configs.CustomerDetailServices;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.ProductsDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.TokenDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.UserDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.Product;
import sv.edu.ues.dam235.minirestaurantmanager.entities.User;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.UserRepository;
import sv.edu.ues.dam235.minirestaurantmanager.services.AuthServices;
import sv.edu.ues.dam235.minirestaurantmanager.utilities.JwtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AuthServicesImpl implements AuthServices {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerDetailServices customerDetailServices;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private AuthServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public TokenDTO login(String user, String pass) {
        TokenDTO token = new TokenDTO();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user, pass)
            );
            if (authentication.isAuthenticated()) {
                UserDetails usuarioDetail = (UserDetails)
                        authentication.getPrincipal();
                    token = jwtUtil.generateToken(user, usuarioDetail);
                    return token;
            }
        } catch (BadCredentialsException bad) {
            token.setMsj("Credenciales incorrectas!");
            return null;
        } catch (Exception e) {
            log.error("{}", e);
            token.setMsj("Error innesperado");
            return null;
        }
        return null;
    }

    @Override
    public Boolean findByEmail(String email){
        Optional<User> resultado = Optional.ofNullable(this.userRepository.findByEmail(email));
        User user = new User();
        if(resultado.orElse(null) != null) {
            return  true;
        }
        return false;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setOwnerId(userDTO.getOwnerId());
        user = userRepository.save(user);
        userDTO = new UserDTO(user.getId(),user.getFullName(),user.getEmail(),user.getPassword(),user.getRole(),user.getOwnerId());
        return userDTO;
    }

    @Override
    public void deleteById(Integer id){
        this.userRepository.deleteById(id);
    }

    @Override
    public Boolean existsById(Integer id){
        return this.userRepository.existsById(id);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        user.setOwnerId(userDTO.getOwnerId());
        user = userRepository.save(user);
        userDTO = new UserDTO(user.getId(),user.getFullName(),user.getEmail(),user.getPassword(),user.getRole(),user.getOwnerId());
        return userDTO;
    }

    @Override
    public UserDTO getUser(Integer id) {
        UserDTO userDTO;
        Optional<User> resultado = this.userRepository.findById(id);
        User user = new User();
        if(resultado.orElse(null) != null) {
            user = resultado.get();
            userDTO = new UserDTO(user.getId(),user.getFullName(),user.getEmail(),user.getPassword(),user.getRole(),user.getOwnerId());
            return userDTO;
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = new ArrayList<>();
        List<User> users = this.userRepository.findAll();
        for (User user : users) {
            list.add(new UserDTO(user.getId(),user.getFullName(),user.getEmail(),user.getPassword(),user.getRole(),user.getOwnerId()));
        }
        return list;
    }

}