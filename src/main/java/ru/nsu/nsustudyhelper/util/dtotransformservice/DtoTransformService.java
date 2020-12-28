package ru.nsu.nsustudyhelper.util.dtotransformservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.*;
import ru.nsu.nsustudyhelper.entity.EntryYear;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Mark;
import ru.nsu.nsustudyhelper.entity.Semester;
import ru.nsu.nsustudyhelper.entity.security.User;

@Service
@RequiredArgsConstructor
public class DtoTransformService {
    private final ModelMapper modelMapper;

    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public EntryYearDto convertToEntryYearDto(EntryYear entryYear) {
        return modelMapper.map(entryYear, EntryYearDto.class);
    }

    public SemesterDto convertToSemesterDto(Semester semester) {
        return modelMapper.map(semester, SemesterDto.class);
    }

    public ExaminationProcessDto convertToExaminationProcessDto(ExaminationProcess examinationProcess) {
        return modelMapper.map(examinationProcess, ExaminationProcessDto.class);
    }

    public MarkDto convertToMarkDto(Mark mark) {
        return modelMapper.map(mark, MarkDto.class);
    }
}
