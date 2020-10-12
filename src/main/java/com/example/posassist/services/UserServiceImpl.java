package com.example.posassist.services;
import com.example.posassist.dto.request.SignUpDTO;
import com.example.posassist.dto.request.UpdateUserDTO;
import com.example.posassist.entities.Role;
import com.example.posassist.entities.User;
import com.example.posassist.enums.RoleName;
import com.example.posassist.exceptions.BadRequestException;
import com.example.posassist.exceptions.ResourceNotFoundException;
import com.example.posassist.repositories.RoleRepository;
import com.example.posassist.repositories.UserRepository;
import com.example.posassist.services.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	@Transactional
	public User findUserById(Long id) throws ResourceNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new ResourceNotFoundException("This user does not exist");
		}
		return user.get();
	}

	@Override
	@Transactional
	public User findUserByName(String name) throws ResourceNotFoundException {
		Optional<User> user = userRepository.findByName(name);
		if(!user.isPresent())
			throw new ResourceNotFoundException("User with name: "+name+" does not exist");
		return user.get();
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User saveUser(SignUpDTO signUpRequest) throws BadRequestException {


		if(userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("This Email Address Is Already Taken");
		}

		// Creating user's account
		User user = User.builder()
				.name(signUpRequest.getName())
				.password(passwordEncoder.encode(signUpRequest.getPassword()))
				.email(signUpRequest.getEmail())
				.build();

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch(role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause: User Role not found."));
					roles.add(adminRole);
					break;

				case "STAFF":
					Role staffRole = roleRepository.findByName(RoleName.ROLE_STAFF)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause: User Role not found."));
					roles.add(staffRole);
					break;

				case "DELIVERY":
					Role deliveryRole = roleRepository.findByName(RoleName.ROLE_DELIVERY)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause : User Role not found."));
					roles.add(deliveryRole);
					break;

				default:
					Role customerRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause: User Role not found."));
					roles.add(customerRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);

		return user;
	}

	@Override
	@Transactional
	public void deleteUser(Long userId) {

		userRepository.deleteById(userId);
	}

	@Override
	@Transactional
	public User updateUser(Long id, UpdateUserDTO updateUserDTO) throws ResourceNotFoundException{
		Optional<User> optional = userRepository.findById(id);
		if(!optional.isPresent())
			throw new ResourceNotFoundException("Invalid user");
		User user = optional.get();
		user.setName(updateUserDTO.getName());
		user.setEmail(updateUserDTO.getEmail());

		Set<String> strRoles = updateUserDTO.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch(role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause: User Role not found."));
					roles.add(adminRole);
					break;

				case "STAFF":
					Role staffRole = roleRepository.findByName(RoleName.ROLE_STAFF)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause: User Role not found."));
					roles.add(staffRole);
					break;

				case "DELIVERY":
					Role deliveryRole = roleRepository.findByName(RoleName.ROLE_DELIVERY)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause : User Role not found."));
					roles.add(deliveryRole);
					break;

				default:
					Role customerRole = roleRepository.findByName(RoleName.ROLE_CUSTOMER)
							.orElseThrow(() -> new BadRequestException("Fail! -> Cause: User Role not found."));
					roles.add(customerRole);
			}
		});
		user.setRoles(roles);
		userRepository.save(user);
		return user;
	}
}