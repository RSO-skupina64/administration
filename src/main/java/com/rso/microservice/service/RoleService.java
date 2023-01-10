package com.rso.microservice.service;


import com.rso.microservice.entity.Role;
import com.rso.microservice.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    public static final Logger log = LoggerFactory.getLogger(RoleService.class);

    final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        if (roleRepository.existsById(role.getId())) {
            return roleRepository.save(role);
        }

        return null;
    }

    public void removeRole(Long id) {
        if (roleRepository.existsById(id)) {
            roleRepository.deleteById(id);
        }
    }

}
