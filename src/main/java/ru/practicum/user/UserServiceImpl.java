package ru.practicum.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserDto.mapToUserDto(users);
    }

    @Override
    @Transactional
    public UserDto saveUser(UserDto userDto) {
        User user = userRepository.save(UserDto.mapToNewUser(userDto));
        return UserDto.mapToUserDto(user);
    }
}