package ru.nsu.nsustudyhelper.dto;

import lombok.Data;
import ru.nsu.nsustudyhelper.entity.security.User;

@Data
public class ExaminationCommentDto {
    private User user;
    private String commentText;
}
