package ru.nsu.nsustudyhelper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ExamCommentsDto {
    private ExaminationCommentDto currentUserComment;
    private Set<ExaminationCommentDto> comments;
}
