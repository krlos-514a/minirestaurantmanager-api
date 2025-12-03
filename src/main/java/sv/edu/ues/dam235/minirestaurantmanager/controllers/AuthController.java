package sv.edu.ues.dam235.minirestaurantmanager.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.LoginDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.ProductsDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.TokenDTO;
import sv.edu.ues.dam235.minirestaurantmanager.dtos.UserDTO;
import sv.edu.ues.dam235.minirestaurantmanager.entities.User;
import sv.edu.ues.dam235.minirestaurantmanager.services.AuthServices;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    final private AuthServices authServices;

    private AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = authServices.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.ok(users);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO authRequest) {
        try {
            System.out.println("DTO enviado : " +
                    authRequest.toString());
            TokenDTO token = authServices.login(authRequest.getUser(),
                    authRequest.getPass());
            if (token == null) {
                return ResponseEntity.status(401).build();
            } else {
                return ResponseEntity.ok(token);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
        try {
            UserDTO user = authServices.getUser(id);
            if (user == null) {
                return ResponseEntity.status(404).build();
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userRequest) {
        Boolean exist = authServices.findByEmail(userRequest.getEmail());
        if (exist) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        try {
            System.out.println("DTO enviado : " + userRequest.toString());
            UserDTO userDTO = authServices.registerUser(userRequest);
            if (userDTO.getId() == null) {
                return ResponseEntity.status(401).build();
            } else {
                return ResponseEntity.ok(userDTO);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!authServices.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            authServices.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }

    @PostMapping("/update")
    public ResponseEntity<UserDTO> updateEmpleado(@RequestBody UserDTO userRequest) {
        try {
            System.out.println("DTO enviado : " +
                    userRequest.toString());
            UserDTO user = authServices.updateUser(userRequest);
            if (user.getId() == null) {
                return ResponseEntity.status(401).build();
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }
}