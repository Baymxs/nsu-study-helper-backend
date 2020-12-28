package ru.nsu.nsustudyhelper.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.entity.security.User;
import ru.nsu.nsustudyhelper.security.user.service.UserService;
import ru.nsu.nsustudyhelper.security.jwt.JwtUser;
import ru.nsu.nsustudyhelper.security.jwt.JwtUserFactory;


@Service("JwtUserDetailService")
@RequiredArgsConstructor
@Slf4j
public class JwtUserDetailService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = userService.findByEmail(login);

        if (user == null) {
            log.info("IN loadUserByUsername - user with login {} not found", login);

            throw new UsernameNotFoundException("User with login " + login + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with login: {} successfully loaded", login);
        return jwtUser;
    }
}

