package ru.nsu.nsustudyhelper.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRegistrationDto extends UserDto {
    private String password;
}