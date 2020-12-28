package ru.nsu.nsustudyhelper.subjectstatistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.*;
import ru.nsu.nsustudyhelper.entity.*;
import ru.nsu.nsustudyhelper.entity.security.User;
import ru.nsu.nsustudyhelper.repository.ExaminationCommentRepository;
import ru.nsu.nsustudyhelper.repository.ExaminationProcessRepository;
import ru.nsu.nsustudyhelper.repository.MarkRepository;
import ru.nsu.nsustudyhelper.repository.UserRepository;
import ru.nsu.nsustudyhelper.util.dtotransformservice.DtoTransformService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamService {
    private final DtoTransformService dtoTransformService;
    private final MarkRepository markRepository;
    private final UserRepository userRepository;
    private final ExaminationProcessRepository examinationProcessRepository;
    private final ExaminationCommentRepository examinationCommentRepository;

    public UserMarkDetailsDto getUserDetails(Principal principal, long examinationId) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        MarkDto markDto = null;
        if (principal != null) {
            User user = userRepository.findByName(principal.getName());
            markDto = dtoTransformService.convertToMarkDto(markRepository.
                    findByUserAndExaminationProcess(user, examinationProcess));
        }


        Set<MarkStatisticDto> set = new HashSet<>();

        MarkStatisticDto mark5 =  new MarkStatisticDto(new MarkDto(5));
        MarkStatisticDto mark4 =  new MarkStatisticDto(new MarkDto(4));
        MarkStatisticDto mark3 =  new MarkStatisticDto(new MarkDto(3));
        MarkStatisticDto mark2 =  new MarkStatisticDto(new MarkDto(2));
        for (Mark mark : markRepository.findAllByExaminationProcess(examinationProcess)) {
            if (mark.getMark() == 5) {
                mark5.setCount(mark5.getCount() + 1);
            } else if (mark.getMark() == 4) {
                mark4.setCount(mark4.getCount() + 1);
            } else if (mark.getMark() == 3) {
                mark3.setCount(mark3.getCount() + 1);
            } else if (mark.getMark() == 2) {
                mark2.setCount(mark2.getCount() + 1);
            }
        }

        set.add(mark5);
        set.add(mark4);
        set.add(mark3);
        set.add(mark2);

        UserMarkDetailsDto userMarkDetailsDto = new UserMarkDetailsDto();

        userMarkDetailsDto.setCurrentUserMark(markDto);
        userMarkDetailsDto.setMarkStatistics(set);

        return userMarkDetailsDto;
    }

    public void setUserMark(Principal principal, long examinationId, MarkSettingDto markSettingDto) {
        User user = userRepository.findByName(principal.getName());

        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        Mark mark = new Mark();

        mark.setExaminationProcess(examinationProcess);
        mark.setUser(user);

        if (markSettingDto.getMark() == null) {
            mark.setMark(null);
        } else if (markSettingDto.getMark() > 5 && markSettingDto.getMark() < 2) {
            throw new IllegalArgumentException("Оценка " + markSettingDto.getMark() + " явялется некорректной");
        } else {
            mark.setMark(markSettingDto.getMark());
        }

        markRepository.save(mark);
    }

    public Set<TeacherDto> getExamTeachers(long examinationId) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        Set<TeacherDto> set = new HashSet<>();

        for (Teacher teacher : examinationProcess.getTeachers()) {
            set.add(dtoTransformService.convertToTeacherDto(teacher));
        }

        return set;
    }

    public ExamCommentsDto getExamComments(Principal principal, long examinationId) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        Set<ExaminationCommentDto> set = new HashSet<>();

        for (ExaminationComment examinationComment : examinationCommentRepository.findAllByExaminationProcess(examinationProcess)) {
            set.add(dtoTransformService.convertToExaminationDto(examinationComment));
        }

        ExamCommentsDto examCommentsDto = new ExamCommentsDto();

        examCommentsDto.setComments(set);

        if (principal == null) {
            examCommentsDto.setCurrentUserComment(null);
        } else {
            User user = userRepository.findByName(principal.getName());
            examCommentsDto.setCurrentUserComment(dtoTransformService.convertToExaminationDto(examinationCommentRepository.findByExaminationProcessAndUser(examinationProcess, user)));
        }

        return examCommentsDto;
    }

    public void setExamComment(Principal principal, long examinationId, ExamCommentSettingDto examCommentSettingDto) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        User user = userRepository.findByName(principal.getName());



        ExaminationComment examComment = examinationCommentRepository.findByExaminationProcessAndUser(examinationProcess, user);

        if (examComment == null) {
            ExaminationComment examinationComment = new ExaminationComment();

            examinationComment.setExaminationProcess(examinationProcess);
            examinationComment.setUser(user);
            examinationComment.setCommentText(examCommentSettingDto.getText());

            examinationCommentRepository.save(examinationComment);
        } else {
            examComment.setCommentText(examCommentSettingDto.getText());

            examinationCommentRepository.save(examComment);
        }
    }
}
