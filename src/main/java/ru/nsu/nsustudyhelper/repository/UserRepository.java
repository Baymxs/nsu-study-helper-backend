package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.nsustudyhelper.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

