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

    UserDTO toDto(User user);

    List<UserDTO> toDtos(List<User> users);

    @Mapping(target = "enabled", ignore = true)
    User toUser(UserDTO userDTO);

    List<User> toUsers(List<UserDTO> userDTOs);

    @Mapping(target = "enabled", ignore = true)
    void updateUserFromDto(UserDTO userDTO, @MappingTarget User user);
}
