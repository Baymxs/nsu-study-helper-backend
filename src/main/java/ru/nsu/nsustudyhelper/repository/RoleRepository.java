package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.nsustudyhelper.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
