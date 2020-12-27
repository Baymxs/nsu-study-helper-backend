package ru.nsu.nsustudyhelper.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nsustudyhelper.dto.Credentials;
import ru.nsu.nsustudyhelper.dto.UserDto;
import ru.nsu.nsustudyhelper.dto.UserRegistrationDto;
import ru.nsu.nsustudyhelper.entity.PasswordRestoreToken;
import ru.nsu.nsustudyhelper.entity.User;
import ru.nsu.nsustudyhelper.repository.PasswordRestoreTokenRepository;
import ru.nsu.nsustudyhelper.security.email.service.EmailService;
import ru.nsu.nsustudyhelper.security.jwt.JwtTokenProvider;
import ru.nsu.nsustudyhelper.security.token.service.PasswordRestoreTokenService;
import ru.nsu.nsustudyhelper.security.user.service.UserService;
import ru.nsu.nsustudyhelper.util.AddressProperties;
import ru.nsu.nsustudyhelper.util.dtotransformservice.DtoTransformService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    @Lazy
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final AddressProperties addressProperties;

    private final PasswordRestoreTokenRepository restoreRepository;

    private final PasswordRestoreTokenService passwordRestoreTokenService;

    private final EmailService emailService;

    private final PasswordEncoder bCryptPasswordEncoder;

    private final DtoTransformService userDtoTransformService;

    @Transactional
    public String generateToken(Credentials credentials) {
        String login = credentials.getLogin();
        String password = credentials.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        User user = userService.findByEmail(login);

        log.info("User {} logged in", login);

        return jwtTokenProvider.createToken(login, user.getRoles());
    }

    public void registerUser(UserRegistrationDto userRegistrationDto) {
        log.info("IN registerUser - user {} wants to be registered", userRegistrationDto.getEmail());

        checkEmail(userRegistrationDto);
        userService.register(userDtoTransformService.convertToUser(userRegistrationDto));
    }

    private void checkEmail(UserDto userDto) {
        if (userService.findByEmail(userDto.getEmail()) != null) {
            throw new IllegalArgumentException("Your email address is already registered: " + userDto.getEmail());
        }
        log.info("IN checkEmail - email {} is available", userDto.getEmail());
    }


    public void restore(String email) {
        User user = userService.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("Wrong email");
        }

        String domainName = addressProperties.getDomainName();
        int port = addressProperties.getPort();

        try {
            PasswordRestoreToken passwordRestoreToken = passwordRestoreTokenService.fillPasswordRestoreToken(user.getId(), new PasswordRestoreToken());
            String confirmUrl = "http://" + domainName + ":" + port + "/api/v1"
                    + "/auth/restore/" + passwordRestoreToken.getStringToken();
            String message = "Здравствуйте, " + user.getName()
                    + "!\nНажмите на ссылку ниже, чтобы поменять пароль:\n" + confirmUrl;
            emailService.sendMessage(user.getEmail(), "Смена пароля", message);
        } catch (Exception e) {
            log.error("Error sending password restore link to user: {}", user.getEmail(), e);
        }
    }

    @Transactional
    public void changePassword(String token, String newPassword) {
        long userId = passwordRestoreTokenService.getUserIdFromToken(token);
        User user = userService.findByUserId(userId);

        changePassword(user, newPassword);
    }

    @Transactional
    public void changePassword(User user, String newPassword) {
        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        restoreRepository.deleteByUserId(user.getId());
        log.info("User {} changed password", user.getEmail());
    }

    public void validateRestoreToken(String token) {
        passwordRestoreTokenService.validate(restoreRepository.findByStringToken(token));
        log.info("Restore token {} was validated", token);
    }
}
