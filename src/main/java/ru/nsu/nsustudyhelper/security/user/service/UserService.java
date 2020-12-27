package ru.nsu.nsustudyhelper.security.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.entity.Role;
import ru.nsu.nsustudyhelper.entity.User;
import ru.nsu.nsustudyhelper.repository.RoleRepository;
import ru.nsu.nsustudyhelper.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(User user) {
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findByName("UNCONFIRMED"));

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user {} was successfully registered", registeredUser);

        return registeredUser;
    }

    public List<User> getAll() {
        List<User> allUsers = userRepository.findAll();

        log.info("IN getAll - {} users found", allUsers.size());

        return allUsers;
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        log.info("IN findByUsername - user: {} found by email {}", user, email);

        return user;
    }

    public User findByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        return user;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);

        log.info("IN delete - user with id: {} successfully deleted", id);
    }
}

