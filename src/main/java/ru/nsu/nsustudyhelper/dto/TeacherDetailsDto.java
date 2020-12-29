package ru.nsu.nsustudyhelper.dto;

import lombok.Data;

@Data
public class TeacherDetailsDto {
    private String birthYear;

    private String status;

    private float rating;

    private TeacherRatingDto currentUserRating;
}
