package sv.edu.ues.dam235.minirestaurantmanager.configs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.edu.ues.dam235.minirestaurantmanager.entities.User;
import sv.edu.ues.dam235.minirestaurantmanager.repositories.UserRepository;


import java.util.ArrayList;

@Service
public class CustomerDetailServices implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;
    @Getter
    private User userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        userDetail = userRepo.findByEmail(username);
        if (userDetail != null) {
            try {
                return new
                        org.springframework.security.core.userdetails.User(userDetail.getEmail(),
                        userDetail.getPassword(),
                        new ArrayList<>());
            } catch (Exception e) {
                return null;
            }
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}