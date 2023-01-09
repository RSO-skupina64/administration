package com.rso.microservice.service;


import com.rso.microservice.entity.User;
import com.rso.microservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public static final Logger log = LoggerFactory.getLogger(UserService.class);

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void removeUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public User updateUser(Long id, User user) {
        user.setId(id);
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }

        return null;
    }

}
