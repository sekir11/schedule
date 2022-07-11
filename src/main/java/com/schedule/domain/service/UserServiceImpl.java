package com.schedule.domain.service;


import com.schedule.common.ErrorDetail;
import com.schedule.common.exception.ApplicationException;
import com.schedule.domain.model.User;
import com.schedule.domain.repository.EventRepository;
import com.schedule.domain.repository.ParticipantRepository;
import com.schedule.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.schedule.common.ErrorCodes.*;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public User login(String name, String password) {
        User result = userRepository.getUser(name);
        if (isNull(result)) {
            throw new ApplicationException(ErrorDetail.builder().errorCode(USER_NOT_FOUND).build());
        }

        if (!result.getPassword().equals(password)) {
            throw new ApplicationException(ErrorDetail.builder().errorCode(PASSWORD_ERROR).build());
        }
        return result;
    }

    @Override
    public User signUp(String name, String password, String address) {
        User existingUser = userRepository.getUser(name);
        if (isNull(existingUser)) {
            userRepository.addUser(name, password, address);
            return userRepository.getUser(name);
        } else {
            throw new ApplicationException(ErrorDetail.builder()
                                                      .errorCode(NAME_IS_ALREADY_IN_USE)
                                                      .argument(name)
                                                      .build());
        }
    }

    @Override
    public List<User> searchUsers(String name) {
        return userRepository.searchUsers(name);
    }

    @Override
    public void editUser(String oldName, String newName, String password, String address) {

        if (oldName.equals(newName)) {
            userRepository.editUser(oldName, password, address);
        } else {
            userRepository.addUser(newName, password, address);
            eventRepository.editCreateUser(oldName, newName);
            participantRepository.editParticipantUserName(oldName, newName);
        }

        userRepository.deleteUser(oldName);
    }
}
