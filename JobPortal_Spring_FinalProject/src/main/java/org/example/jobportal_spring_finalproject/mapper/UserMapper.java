package org.example.jobportal_spring_finalproject.mapper;

import org.example.jobportal_spring_finalproject.model.dto.UserDTO;
import org.example.jobportal_spring_finalproject.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
}
