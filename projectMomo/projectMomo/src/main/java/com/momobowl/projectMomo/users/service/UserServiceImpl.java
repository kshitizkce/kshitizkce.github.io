package com.momobowl.projectMomo.users.service;
import com.momobowl.projectMomo.users.model.Users;
import com.momobowl.projectMomo.users.repositories.UserRepositories;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final UserRepositories userRepository;

    public UserServiceImpl(UserRepositories userRepository) {
        this.userRepository = userRepository;
    }

    // Check if email exists in the database
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    // Save user details in the database
    public void saveUser(Users users) {
        userRepository.save(users);
    }

    // Load user details for authentication (Spring Security integration)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        // Convert Users entity to Spring Security UserDetails
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
//                .roles("USER") // Assign roles (e.g., ROLE_USER)
                .build();
    }
}
