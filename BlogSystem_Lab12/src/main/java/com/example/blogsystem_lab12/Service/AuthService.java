package com.example.blogsystem_lab12.Service;

import com.example.blogsystem_lab12.Api.ApiException;
import com.example.blogsystem_lab12.Model.MyUser;
import com.example.blogsystem_lab12.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    // 1. CRUD
    // 1.1 Get all users (for the admin)
    public List<MyUser> getAllUsers() {
        return authRepository.findAll();
    }

    // 1.2 Add new user (register)
    public void register(MyUser myUser) {
        myUser.setRole("User");
        String hashPassword = new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hashPassword);
        authRepository.save(myUser);
    }

    // 1.3 Update user (edit account)
    public void editAccount(Integer userId, MyUser updatedUser) {
        // 1. Check if user exists
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("User Not Found.");
        }
        // 2. Set new values
        myUser.setUsername(updatedUser.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(updatedUser.getPassword());
        myUser.setPassword(hashPassword);
        // 3. Save changes
        authRepository.save(myUser);
    }

    // 1.4 Delete user (delete account)
    public void deleteAccount(Integer userId) {
        // 1. Check if user exists
        MyUser myUser = authRepository.findMyUserById(userId);
        if (myUser == null) {
            throw new ApiException("User Not Found.");
        }
        authRepository.delete(myUser);
    }

}