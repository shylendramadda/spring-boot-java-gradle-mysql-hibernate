package com.shylu.spring_practice.service;

import com.shylu.spring_practice.domain.User;
import com.shylu.spring_practice.dto.UserDTO;
import com.shylu.spring_practice.exceptions.AlreadyExistsException;
import com.shylu.spring_practice.exceptions.ResourceNotFoundException;
import com.shylu.spring_practice.mapper.UserMapper;
import com.shylu.spring_practice.repo.UserRepository;
import com.shylu.spring_practice.util.Constants;
import com.shylu.spring_practice.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDTO register(UserDTO userDTO) {
        User dbUser = userRepository.findByEmailOrMobile(userDTO.getEmail(), userDTO.getMobile());
        if (dbUser != null) {
            throw new AlreadyExistsException(Constants.userExists);
        }
        userDTO.setUuid(RandomUtils.getUUID());
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        User newUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(newUser);
    }

    public UserDTO getUser(UserDTO userDTO) {
        User dbUser = userRepository.findByEmailOrMobileAndPassword(userDTO.getEmail(), userDTO.getMobile(), userDTO.getPassword());
        if (dbUser == null) {
            throw new ResourceNotFoundException(Constants.userNotExists);
        }
        return UserMapper.INSTANCE.userToUserDTO(dbUser);
    }
}
