package ru.nsu.nsustudyhelper.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserMarkDetailsDto {
    private MarkDto markDto;
    private Set<MarkStatisticDto> markStatistic;
}
