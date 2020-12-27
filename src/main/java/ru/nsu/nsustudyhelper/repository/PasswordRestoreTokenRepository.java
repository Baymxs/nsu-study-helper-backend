package ru.nsu.nsustudyhelper.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.nsustudyhelper.entity.PasswordRestoreToken;

@Repository
public interface PasswordRestoreTokenRepository extends CrudRepository<PasswordRestoreToken, Long> {
    PasswordRestoreToken findByStringToken(String token);

    void deleteByUserId(long userId);
}