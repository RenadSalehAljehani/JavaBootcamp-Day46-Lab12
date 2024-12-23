package com.example.blogsystem_lab12.Controller;

import com.example.blogsystem_lab12.Api.ApiResponse;
import com.example.blogsystem_lab12.Model.MyUser;
import com.example.blogsystem_lab12.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 1. CRUD
    // 1.1 Get all users (for admin)
    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    // 1.2 Add new user (register)
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body(new ApiResponse("User Registered Successfully."));
    }

    // 1.3 Update user (edit account)
    @PutMapping("/editAccount")
    private ResponseEntity editAccount(@AuthenticationPrincipal MyUser myUser, @RequestBody @Valid MyUser updatedUser){
        authService.editAccount(myUser.getId(), updatedUser);
        return ResponseEntity.status(200).body(new ApiResponse("Account Updated Successfully."));
    }

    // 1.4 Delete user (delete account)
    @DeleteMapping("/deleteAccount")
    private ResponseEntity deleteAccount(@AuthenticationPrincipal MyUser myUser){
        authService.deleteAccount(myUser.getId());
        return ResponseEntity.status(200).body(new ApiResponse("Account Deleted Successfully."));
    }



}