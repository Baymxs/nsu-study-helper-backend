package ru.nsu.nsustudyhelper.util.dtotransformservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.nsu.nsustudyhelper.dto.UserDto;
import ru.nsu.nsustudyhelper.entity.User;

@Service
@RequiredArgsConstructor
public class DefaultDtoTransformService implements DtoTransformService {
    private final ModelMapper modelMapper;

    @Override
    public User convertToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
