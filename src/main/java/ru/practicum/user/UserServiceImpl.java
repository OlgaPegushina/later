package ru.practicum.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class UserServiceImpl implements UserService {
    UserRepository repository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = repository.findAll();
        return UserDto.mapToUserDto(users);
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = repository.save(UserDto.mapToNewUser(userDto));
        return UserDto.mapToUserDto(user);
    }
}