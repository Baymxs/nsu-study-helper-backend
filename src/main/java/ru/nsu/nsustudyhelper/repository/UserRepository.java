package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.nsustudyhelper.entity.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByName(String name);
}

