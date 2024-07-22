package com.shylu.spring_practice.mapper;

import com.shylu.spring_practice.domain.User;
import com.shylu.spring_practice.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "password", ignore = true)
    List<UserDTO> toDtos(List<User> users);

    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(UserDTO userDTO);

//    List<User> toUsers(List<UserDTO> userDTOs);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "password", ignore = true)
    User updateUserFromDto(UserDTO userDTO, @MappingTarget User user);
}
