package com.example.posassist.dto.response;

import com.example.posassist.entities.Role;
import com.example.posassist.entities.User;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private Set<Role> roles;

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .roles(user.getRoles())
                .build();
    }
}
