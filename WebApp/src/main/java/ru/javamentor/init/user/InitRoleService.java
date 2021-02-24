package ru.javamentor.init.user;

import org.springframework.stereotype.Component;
import ru.javamentor.model.user.Role;
import ru.javamentor.repository.RoleRepository;

@Component
public class InitRoleService {
    private final RoleRepository roleRepository;

    public InitRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void initRole() {
        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("MANAGER"));
        roleRepository.save(new Role("CLIENT"));
    }
}
