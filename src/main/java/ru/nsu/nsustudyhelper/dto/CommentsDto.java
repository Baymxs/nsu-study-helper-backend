package ru.nsu.nsustudyhelper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CommentsDto {
    private CommentDto currentUserComment;
    private Set<CommentDto> comments;
}
