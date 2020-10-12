package com.example.posassist.services.interfaces;

import java.util.List;

import com.example.posassist.dto.request.SignUpDTO;
import com.example.posassist.dto.request.UpdateUserDTO;
import com.example.posassist.entities.User;

public interface UserService {
	
	  User findUserById(Long id);
	  
	  User findUserByName(String name);
	  
	  List<User> findAllUsers();
	  
	  User saveUser(SignUpDTO user);
	  
	  void deleteUser(Long userId);
	  
	  User updateUser(Long id, UpdateUserDTO updateUserDTO);
	 }