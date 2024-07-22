package com.shylu.spring_practice.service;

import com.shylu.spring_practice.common.StatusResponse;
import com.shylu.spring_practice.domain.User;
import com.shylu.spring_practice.dto.UserDTO;
import com.shylu.spring_practice.exceptions.AlreadyExistsException;
import com.shylu.spring_practice.exceptions.ResourceNotFoundException;
import com.shylu.spring_practice.exceptions.UnauthorizedException;
import com.shylu.spring_practice.mapper.UserMapper;
import com.shylu.spring_practice.repo.UserRepository;
import com.shylu.spring_practice.util.Constants;
import com.shylu.spring_practice.util.RandomUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO register(UserDTO userDTO) {
        Optional<User> dbUser = userRepository.findByEmailOrMobile(userDTO.getEmail(), userDTO.getMobile());
        if (dbUser.isPresent()) {
            throw new AlreadyExistsException(Constants.userAlreadyExists);
        }
        userDTO.setUuid(RandomUtils.getUUID());
        User user = UserMapper.INSTANCE.toUser(userDTO);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User newUser = userRepository.save(user);
        return UserMapper.INSTANCE.toDto(newUser);
    }

    public UserDTO login(UserDTO userDTO) {
        User dbUser = getIfExists(userDTO);
        if (!passwordEncoder.matches(userDTO.getPassword(), dbUser.getPassword())) {
            throw new UnauthorizedException(Constants.invalidCredentials);
        }
        return UserMapper.INSTANCE.toDto(dbUser);
    }

    public StatusResponse update(UserDTO userDTO) {
        User dbUser = getUserByUid(userDTO);
        User newUser = UserMapper.INSTANCE.updateUserFromDto(userDTO, dbUser);
        newUser.setEnabled(true);
        String password = userDTO.getPassword();
        if (!StringUtils.isBlank(password)) {
            newUser.setPassword(passwordEncoder.encode(password));
        } else {
            newUser.setPassword(dbUser.getPassword());
        }
        userRepository.save(newUser);
        return new StatusResponse(Constants.success, 200, Constants.userUpdateSuccess, dbUser);
    }

    public StatusResponse delete(UserDTO userDTO) {
        User dbUser = getUserByUid(userDTO);
        if (!dbUser.getRoles().contains(Constants.admin)) {
            userRepository.deleteByUuid(dbUser.getUuid());
        } else {
            return new StatusResponse(Constants.failure, 404, Constants.canNotDeleteAdmin);
        }
        return new StatusResponse(Constants.success, 200, Constants.userDeleteSuccess);
    }

    public List<UserDTO> getAll() {
        List<User> dbUsers = (List<User>) userRepository.findAll();
        return UserMapper.INSTANCE.toDtos(dbUsers);
    }

    public User getIfExists(UserDTO userDTO) {
        Optional<User> dbUser = userRepository.findByEmailOrMobileOrUsername(
                userDTO.getEmail(),
                userDTO.getMobile(),
                userDTO.getUsername()
        );
        if (dbUser.isEmpty()) {
            throw new ResourceNotFoundException(Constants.userNotFound);
        }
        return dbUser.get();
    }

    private User getUserByUid(UserDTO userDTO) {
        Optional<User> dbUser = userRepository.findByUuid(userDTO.getUuid());
        if (dbUser.isEmpty()) {
            throw new ResourceNotFoundException(Constants.userNotFound);
        }
        return dbUser.get();
    }
}
