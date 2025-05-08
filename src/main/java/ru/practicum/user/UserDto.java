package ru.practicum.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String registrationDate;
    private UserState state;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss");

    public static UserDto mapToUserDto(User user) {
        // преобразуем registrationDate
        String formattedDate = user.getRegistrationDate()
                .atZone(ZoneId.systemDefault())
                .format(formatter);

        return new UserDto(user.getId(), user.getFullName(), user.getEmail(), formattedDate, user.getState());
    }

    public static List<UserDto> mapToUserDto(Iterable<User> users) {
        List<UserDto> result = new ArrayList<>();

        for (User user : users) {
            result.add(mapToUserDto(user));
        }

        return result;
    }

    public static User mapToNewUser(UserDto userDto) {
        User user = new User();

        String[] fullName = userDto.getName().split(" ");
        if (fullName.length == 2) {
            user.setFirstName(fullName[0]);
            user.setLastName(fullName[1]);
        } else {
            throw new IllegalArgumentException("Имя должно состоять из Имени пробел Фамилии");
        }

        user.setEmail(userDto.getEmail());
        user.setState(userDto.getState());
        return user;
    }
}