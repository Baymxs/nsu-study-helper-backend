package ru.nsu.nsustudyhelper.util.dtotransformservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.*;
import ru.nsu.nsustudyhelper.entity.*;
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

    public TeacherDto convertToTeacherDto(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDto.class);
    }

    public ExaminationCommentDto convertToExaminationDto(ExaminationComment examinationComment) {
        return modelMapper.map(examinationComment, ExaminationCommentDto.class);
    }
}
