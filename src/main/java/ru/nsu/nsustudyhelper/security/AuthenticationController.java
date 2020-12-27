package ru.nsu.nsustudyhelper.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.nsu.nsustudyhelper.dto.*;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/sign-in")
    public TokenDto signIn(@RequestBody Credentials credentials) {
        String token = authenticationService.generateToken(credentials);
        return new TokenDto(token);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody UserRegistrationDto userRegistrationDto) {
        authenticationService.registerUser(userRegistrationDto);
    }

    @PostMapping("/restore")
    public void restore(@RequestBody EmailRestoreDto emailRestoreDto) {
        authenticationService.restore(emailRestoreDto.getEmail());
    }

    @GetMapping("/restore/{token}")
    public ModelAndView getRestorePage(@PathVariable String token) {
        authenticationService.validateRestoreToken(token);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/restore.html");

        log.debug("The restore page has been queried.");
        return modelAndView;
    }

    @PostMapping("/restore/{token}")
    public void restore(@RequestBody NewPasswordDto newPasswordDto, @PathVariable String token) {
        String newPassword = newPasswordDto.getNewPassword();
        authenticationService.changePassword(token, newPassword);
    }
}
