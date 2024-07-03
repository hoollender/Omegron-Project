package com.omegron.init;

import com.omegron.model.entity.Role;
import com.omegron.model.enums.RoleEnum;
import com.omegron.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitService implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public InitService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setRole(RoleEnum.USER);
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setRole(RoleEnum.ADMIN);
            roleRepository.save(adminRole);
        }
    }
}