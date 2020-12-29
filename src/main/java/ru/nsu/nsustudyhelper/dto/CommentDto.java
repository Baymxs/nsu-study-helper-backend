package ru.nsu.nsustudyhelper.dto;

import lombok.Data;
import ru.nsu.nsustudyhelper.entity.security.User;

@Data
public class CommentDto {
    private UserDto user;
    private String commentText;
}
