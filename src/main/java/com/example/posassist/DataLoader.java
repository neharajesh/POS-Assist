package com.example.posassist;
import com.example.posassist.entities.Role;
import com.example.posassist.enums.RoleName;
import com.example.posassist.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DataLoader implements ApplicationRunner {

    private RoleRepository roleRepository;

    @Autowired
    public DataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        Optional<Role> roleCustomer= roleRepository.findByName(RoleName.ROLE_CUSTOMER);
        if(!roleCustomer.isPresent())
            roleRepository.save(Role.builder()
                    .name(RoleName.ROLE_CUSTOMER).build());

        Optional<Role> roleStaff = roleRepository.findByName(RoleName.ROLE_STAFF);
        if(!roleStaff.isPresent())
            roleRepository.save(Role.builder()
                    .name(RoleName.ROLE_STAFF).build());

        Optional<Role> roleDelivery = roleRepository.findByName(RoleName.ROLE_DELIVERY);
        if(!roleDelivery.isPresent())
            roleRepository.save(Role.builder()
                    .name(RoleName.ROLE_DELIVERY).build());

        Optional<Role> roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
        if(!roleAdmin.isPresent())
            roleRepository.save(Role.builder()
                    .name(RoleName.ROLE_ADMIN).build());
    }
}