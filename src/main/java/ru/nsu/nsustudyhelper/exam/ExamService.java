package ru.nsu.nsustudyhelper.exam;

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

    public UserMarkDetailsDto getExamDetails(Principal principal, long examinationId) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        ExamMarkDto examMarkDto = null;
        if (principal != null) {
            User user = userRepository.findByName(principal.getName());
            ExamMark examMark = markRepository.
                    findByUserAndExaminationProcess(user, examinationProcess);
            if (examMark != null) {
                examMarkDto = dtoTransformService.convertToMarkDto(examMark);
            }
        }


        Set<MarkStatisticDto> set = new HashSet<>();

        MarkStatisticDto mark5 =  new MarkStatisticDto(new ExamMarkDto(5));
        MarkStatisticDto mark4 =  new MarkStatisticDto(new ExamMarkDto(4));
        MarkStatisticDto mark3 =  new MarkStatisticDto(new ExamMarkDto(3));
        MarkStatisticDto mark2 =  new MarkStatisticDto(new ExamMarkDto(2));
        for (ExamMark examMark : markRepository.findAllByExaminationProcess(examinationProcess)) {
            if (examMark.getMark() == 5) {
                mark5.setCount(mark5.getCount() + 1);
            } else if (examMark.getMark() == 4) {
                mark4.setCount(mark4.getCount() + 1);
            } else if (examMark.getMark() == 3) {
                mark3.setCount(mark3.getCount() + 1);
            } else if (examMark.getMark() == 2) {
                mark2.setCount(mark2.getCount() + 1);
            }
        }

        set.add(mark5);
        set.add(mark4);
        set.add(mark3);
        set.add(mark2);

        UserMarkDetailsDto userMarkDetailsDto = new UserMarkDetailsDto();

        userMarkDetailsDto.setCurrentUserMark(examMarkDto);
        userMarkDetailsDto.setMarkStatistics(set);

        return userMarkDetailsDto;
    }

    public void setUserMark(Principal principal, long examinationId, MarkSettingDto markSettingDto) {
        User user = userRepository.findByName(principal.getName());

        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        ExamMark examMark = markRepository.
                findByUserAndExaminationProcess(user, examinationProcess);
        if (examMark == null) {
            examMark = new ExamMark();
            examMark.setExaminationProcess(examinationProcess);
            examMark.setUser(user);
        }

        if (markSettingDto.getMark() == null) {
            markRepository.delete(examMark);
        } else if (markSettingDto.getMark() > 5 && markSettingDto.getMark() < 2) {
            throw new IllegalArgumentException("Оценка " + markSettingDto.getMark() + " явялется некорректной");
        } else {
            examMark.setMark(markSettingDto.getMark());
            markRepository.save(examMark);
        }
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

    public CommentsDto getExamComments(Principal principal, long examinationId) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        Set<CommentDto> set = new HashSet<>();

        for (ExaminationComment examinationComment : examinationCommentRepository.findAllByExaminationProcessOrderByIdDesc(examinationProcess)) {
            set.add(dtoTransformService.convertToExaminationCommentDto(examinationComment));
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setComments(set);

        if (principal == null) {
            commentsDto.setCurrentUserComment(null);
        } else {
            User user = userRepository.findByName(principal.getName());

            ExaminationComment comment = examinationCommentRepository.findByExaminationProcessAndUser(examinationProcess, user);
            if (comment != null) {
                commentsDto.setCurrentUserComment(dtoTransformService.convertToExaminationCommentDto(comment));
            }
        }
        return commentsDto;
    }

    public void setExamComment(Principal principal, long examinationId, CommentSettingDto commentSettingDto) {
        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        User user = userRepository.findByName(principal.getName());
        ExaminationComment examComment = examinationCommentRepository.findByExaminationProcessAndUser(examinationProcess, user);

        if (examComment == null) {
            if (commentSettingDto.getCommentText().length() != 0) {
                examComment = new ExaminationComment();

                examComment.setExaminationProcess(examinationProcess);
                examComment.setUser(user);
                examComment.setCommentText(commentSettingDto.getCommentText());

                examinationCommentRepository.save(examComment);
            }
        } else
        {
            examComment.setCommentText(commentSettingDto.getCommentText());
            if (commentSettingDto.getCommentText().length() != 0) {
                examinationCommentRepository.save(examComment);
            } else {
                examinationCommentRepository.delete(examComment);
            }
        }
    }
}
