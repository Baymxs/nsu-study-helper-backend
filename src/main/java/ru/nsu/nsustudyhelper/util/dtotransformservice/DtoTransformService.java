package ru.nsu.nsustudyhelper.util.dtotransformservice;

import ru.nsu.nsustudyhelper.dto.UserDto;
import ru.nsu.nsustudyhelper.entity.User;

public interface DtoTransformService {
    User convertToUser(UserDto userDto);
}
