package ru.nsu.nsustudyhelper.teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.*;
import ru.nsu.nsustudyhelper.entity.*;
import ru.nsu.nsustudyhelper.entity.security.User;
import ru.nsu.nsustudyhelper.repository.*;
import ru.nsu.nsustudyhelper.util.dtotransformservice.DtoTransformService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherService {
    private final DtoTransformService dtoTransformService;
    private final TeacherRatingRepository teacherRatingRepository;
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherCommentRepository teacherCommentRepository;

    public TeacherDetailsDto getTeacherDetails(Principal principal, long teacherId) {
        Teacher teacher = teacherRepository.
                findById(teacherId).
                orElseThrow(() -> new IllegalArgumentException("Преподаватель " + teacherId + " не найден."));

        TeacherRatingDto teacherRatingDto = null;
        if (principal != null) {
            User user = userRepository.findByName(principal.getName());
            TeacherRating teacherRating = teacherRatingRepository.
                    findByUserAndTeacher(user, teacher);
            if (teacherRating != null) {
                teacherRatingDto = dtoTransformService.convertToRatingDto(teacherRating);
            }
        }

        TeacherDetailsDto teacherDetailsDto = new TeacherDetailsDto();
        teacherDetailsDto.setBirthYear(teacher.getBirthYear());
        teacherDetailsDto.setStatus(teacher.getTStatus());
        teacherDetailsDto.setCurrentUserRating(teacherRatingDto);

        Set<TeacherRating> teacherRatings = teacherRatingRepository.findAllByTeacher(teacher);
        float sumRating = 0;
        for (TeacherRating teacherRating : teacherRatings) {
            sumRating += teacherRating.getRating();
        }
        float avgRating = sumRating/teacherRatings.size();
        if (teacherRatings.isEmpty()) {
            avgRating = sumRating;
        }

        teacherDetailsDto.setRating(avgRating);
        return teacherDetailsDto;
    }

    public void setUserRating(Principal principal, long teacherId, RatingSettingDto ratingSettingDto) {
        User user = userRepository.findByName(principal.getName());

        Teacher teacher = teacherRepository.
                findById(teacherId).
                orElseThrow(() -> new IllegalArgumentException("Преподаватель " + teacherId + " не найден."));

        TeacherRating teacherRating = teacherRatingRepository.
                findByUserAndTeacher(user, teacher);

        if (teacherRating == null) {
            teacherRating = new TeacherRating();
            teacherRating.setTeacher(teacher);
            teacherRating.setUser(user);
        }

        if (ratingSettingDto.getRating() == null) {
            teacherRatingRepository.delete(teacherRating);
        } else if (ratingSettingDto.getRating() > 10 && ratingSettingDto.getRating() < 1) {
            throw new IllegalArgumentException("Рейтинг " + ratingSettingDto.getRating() + " явялется некорректным");
        } else {
            teacherRating.setRating(ratingSettingDto.getRating());
            teacherRatingRepository.save(teacherRating);
        }
    }

    public CommentsDto getTeacherComments(Principal principal, long teacherId) {
        Teacher teacher = teacherRepository.
                findById(teacherId).
                orElseThrow(() -> new IllegalArgumentException("Преподаватель " + teacherId + " не найден."));

        List<CommentDto> list = new ArrayList<>();

        for (TeacherComment teacherComment : teacherCommentRepository.findAllByTeacherOrderByIdDesc(teacher)) {
            list.add(dtoTransformService.convertToTeacherCommentDto(teacherComment));
        }

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setComments(list);

        if (principal == null) {
            commentsDto.setCurrentUserComment(null);
        } else {
            User user = userRepository.findByName(principal.getName());

            TeacherComment comment = teacherCommentRepository.findByTeacherAndUser(teacher, user);
            if (comment != null) {
                commentsDto.setCurrentUserComment(dtoTransformService.convertToTeacherCommentDto(comment));
            }
        }
        return commentsDto;
    }

    public void setTeacherComment(Principal principal, long teacherId, CommentSettingDto commentSettingDto) {
        Teacher teacher = teacherRepository.
                findById(teacherId).
                orElseThrow(() -> new IllegalArgumentException("Преподаватель " + teacherId + " не найден."));

        User user = userRepository.findByName(principal.getName());
        TeacherComment teacherComment = teacherCommentRepository.findByTeacherAndUser(teacher, user);

        if (teacherComment == null) {
            if (commentSettingDto.getCommentText().length() != 0) {
                teacherComment = new TeacherComment();

                teacherComment.setTeacher(teacher);
                teacherComment.setUser(user);
                teacherComment.setCommentText(commentSettingDto.getCommentText());

                teacherCommentRepository.save(teacherComment);
            }
        } else
        {
            teacherComment.setCommentText(commentSettingDto.getCommentText());
            if (commentSettingDto.getCommentText().length() != 0) {
                teacherCommentRepository.save(teacherComment);
            } else {
                teacherCommentRepository.delete(teacherComment);
            }
        }
    }
}

