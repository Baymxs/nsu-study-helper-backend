package ru.nsu.nsustudyhelper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserMarkDetailsDto {
    private MarkDto currentUserMark;
    private Set<MarkStatisticDto> markStatistics;
}
