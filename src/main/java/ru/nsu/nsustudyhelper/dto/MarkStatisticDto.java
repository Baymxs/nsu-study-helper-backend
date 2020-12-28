package ru.nsu.nsustudyhelper.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MarkStatisticDto {
    private final MarkDto mark;
    private int count = 0;
}
