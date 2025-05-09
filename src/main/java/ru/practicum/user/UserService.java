package ru.practicum.user;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserService {
    List<UserDto> getAllUsers();

    @Transactional
    UserDto saveUser(UserDto userDto);
}