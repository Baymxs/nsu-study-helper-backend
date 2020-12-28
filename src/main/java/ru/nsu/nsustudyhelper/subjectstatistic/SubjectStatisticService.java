package ru.nsu.nsustudyhelper.subjectstatistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.ExaminationProcessDto;
import ru.nsu.nsustudyhelper.dto.MarkDto;
import ru.nsu.nsustudyhelper.dto.MarkStatisticDto;
import ru.nsu.nsustudyhelper.entity.ExaminationProcess;
import ru.nsu.nsustudyhelper.entity.Mark;
import ru.nsu.nsustudyhelper.entity.security.User;
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
public class SubjectStatisticService {
    private final DtoTransformService dtoTransformService;
    private final MarkRepository markRepository;
    private final UserRepository userRepository;
    private final ExaminationProcessRepository examinationProcessRepository;

    public MarkDto getMark(Principal principal, long examinationId) {
        User user = userRepository.findByName(principal.getName());

        ExaminationProcess examinationProcess = examinationProcessRepository.
                findById(examinationId).
                orElseThrow(() -> new IllegalArgumentException("Экзамен " + examinationId + " не найден."));

        Mark mark = markRepository.findByUserAndExaminationProcess(user, examinationProcess);

        return dtoTransformService.convertToMarkDto(mark);
    }

    public Set<MarkStatisticDto> getMarkStatistic(Principal principal) {
        User user = userRepository.findByName(principal.getName());
        Set<MarkStatisticDto> set = new HashSet<>();

        MarkStatisticDto mark5 =  new MarkStatisticDto(5);
        MarkStatisticDto mark4 =  new MarkStatisticDto(4);
        MarkStatisticDto mark3 =  new MarkStatisticDto(3);
        MarkStatisticDto mark2 =  new MarkStatisticDto(2);


        for (Mark mark : markRepository.findAllByUser(user)) {
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

        return set;
    }
}
