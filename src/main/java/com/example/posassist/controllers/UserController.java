package com.example.posassist.controllers;

import com.example.posassist.dto.request.UpdateUserDTO;
import com.example.posassist.dto.response.UserResponse;
import com.example.posassist.security.services.*;
import com.example.posassist.dto.request.SignUpDTO;
import com.example.posassist.exceptions.BadRequestException;
import com.example.posassist.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {
    public static final String BASE_URL = "/api/v1/user";

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createUser(@Valid @RequestBody SignUpDTO signUpRequest) throws BadRequestException {
        userService.saveUser(signUpRequest);
        return ResponseEntity.ok().body("User created successfully!" );
    }

    @DeleteMapping("{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>("The user has been deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok().body(userService.findUserById(userId));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUser(@Valid @RequestParam Long id, @RequestBody UpdateUserDTO updateForm){
        userService.updateUser(id, updateForm);
        return ResponseEntity.ok().body("User details updated successfully");
    }


    @GetMapping("/info")
    public ResponseEntity<?> user() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrinciple user = (UserPrinciple) auth.getPrincipal();
        return ResponseEntity.ok().body(UserResponse.from(userService.findUserById(user.getId())));
    }
}
