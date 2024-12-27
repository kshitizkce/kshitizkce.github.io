package com.momobowl.projectMomo.users.service;

import com.momobowl.projectMomo.users.model.Users;
import com.momobowl.projectMomo.users.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    @Autowired
    private UserRepositories userRepository;

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public Users saveUser(Users users) {
        return userRepository.save(users);
    }


    public boolean validateLogin(String email, String password) {
        Users users = userRepository.findByEmail(email);
        if (users != null) {
            return true;
        }
        return false;
    }
}