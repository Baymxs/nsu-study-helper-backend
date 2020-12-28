package ru.nsu.nsustudyhelper.security.token.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.entity.security.PasswordRestoreToken;
import ru.nsu.nsustudyhelper.repository.PasswordRestoreTokenRepository;

import java.util.Date;
import java.util.UUID;


@Service
public class PasswordRestoreTokenService {
    @Value("${jwt.token.expired}")
    private final Long validityInMilliSeconds;

    private final PasswordRestoreTokenRepository passwordRestoreTokenRepository;

    @Autowired
    public PasswordRestoreTokenService(
            @Value("${jwt.token.expired}") Long validityInMilliSeconds,
            PasswordRestoreTokenRepository passwordRestoreTokenRepository
    ) {
        this.validityInMilliSeconds = validityInMilliSeconds;
        this.passwordRestoreTokenRepository = passwordRestoreTokenRepository;
    }

    public PasswordRestoreToken fillPasswordRestoreToken(long userId, PasswordRestoreToken passwordRestoreToken) {
        passwordRestoreToken.setUserId(userId);
        passwordRestoreToken.setExpirationDate(new Date(new Date().getTime() + validityInMilliSeconds));
        passwordRestoreToken.setStringToken(UUID.randomUUID().toString());

        return passwordRestoreTokenRepository.save(passwordRestoreToken);
    }

    public long getUserIdFromToken(String stringToken) {
        PasswordRestoreToken passwordRestoreToken = passwordRestoreTokenRepository.findByStringToken(stringToken);
        validate(passwordRestoreToken);
        return passwordRestoreToken.getUserId();
    }

    public void validate(PasswordRestoreToken passwordRestoreToken) {
        if (passwordRestoreToken == null) {
            throw new IllegalArgumentException("Wrong token");
        }

        if (isExpired(passwordRestoreToken)) {
            throw new IllegalArgumentException("Token was expired");
        }
    }

    private boolean isExpired(PasswordRestoreToken passwordRestoreToken) {
        return passwordRestoreToken.getExpirationDate().before(new Date());
    }
}