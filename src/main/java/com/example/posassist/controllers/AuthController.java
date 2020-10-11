package com.example.posassist.controllers;

import com.example.posassist.dto.request.LoginDTO;
import com.example.posassist.dto.request.SignUpDTO;
import com.example.posassist.dto.response.JwtResponse;
import com.example.posassist.entities.Role;
import com.example.posassist.exceptions.BadRequestException;
import com.example.posassist.repositories.RoleRepository;
import com.example.posassist.security.jwt.JwtProvider;
import com.example.posassist.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(AuthController.BASE_URL)
public class AuthController {
    static final String BASE_URL = "/api/v1/auth";
    private final RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtProvider jwtProvider;

    public AuthController(RoleRepository roleRepository, AuthenticationManager authenticationManager, UserService userService, JwtProvider jwtProvider) {
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpDTO signUpRequest) throws BadRequestException {
        userService.saveUser(signUpRequest);
        return ResponseEntity.ok().body("User registered successfully!");
    }

    @GetMapping("/roles")
    public ResponseEntity<String> getRoles() throws BadRequestException {
        List<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok().body(roles.toString());
    }
}