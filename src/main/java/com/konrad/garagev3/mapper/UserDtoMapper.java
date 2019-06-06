package com.konrad.garagev3.mapper;

import com.konrad.garagev3.model.dao.User;
import com.konrad.garagev3.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
