package com.shylu.spring_practice.service;

import com.shylu.spring_practice.common.StatusResponse;
import com.shylu.spring_practice.domain.User;
import com.shylu.spring_practice.dto.UserDTO;
import com.shylu.spring_practice.exceptions.AlreadyExistsException;
import com.shylu.spring_practice.exceptions.ResourceNotFoundException;
import com.shylu.spring_practice.mapper.UserMapper;
import com.shylu.spring_practice.repo.UserRepository;
import com.shylu.spring_practice.util.Constants;
import com.shylu.spring_practice.util.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(UserDTO userDTO) {
        Optional<User> dbUser = userRepository.findByEmailOrMobile(userDTO.getEmail(), userDTO.getMobile());
        if (dbUser.isPresent()) {
            throw new AlreadyExistsException(Constants.userExists);
        }
        userDTO.setUuid(RandomUtils.getUUID());
        User user = UserMapper.INSTANCE.toUser(userDTO);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return UserMapper.INSTANCE.toDto(newUser);
    }

    public UserDTO get(UserDTO userDTO) {
        User dbUser = getIfExists(userDTO);
        return UserMapper.INSTANCE.toDto(dbUser);
    }

    public User getIfExists(UserDTO userDTO) {
        Optional<User> dbUser = userRepository.findByEmailOrMobileAndPassword(userDTO.getEmail(), userDTO.getMobile(), userDTO.getPassword());
        if (dbUser.isEmpty()) {
            throw new ResourceNotFoundException(Constants.userNotExists);
        }
        return dbUser.get();
    }

    public StatusResponse update(UserDTO userDTO) {
        User dbUser = getIfExists(userDTO);
        UserMapper.INSTANCE.updateUserFromDto(userDTO, dbUser);
        dbUser.setEnabled(true);
        dbUser.setPassword(passwordEncoder.encode(dbUser.getPassword()));
        userRepository.save(dbUser);
        return new StatusResponse(Constants.success, 200, Constants.userUpdateSuccess, dbUser);
    }

    public StatusResponse delete(UserDTO userDTO) {
        User dbUser = getIfExists(userDTO);
        userRepository.delete(dbUser);
        return new StatusResponse(Constants.success, 200, Constants.userDeleteSuccess, dbUser);
    }

    public List<UserDTO> getAll() {
        List<User> dbUsers = (List<User>) userRepository.findAll();
        return UserMapper.INSTANCE.toDtos(dbUsers);
    }
}
