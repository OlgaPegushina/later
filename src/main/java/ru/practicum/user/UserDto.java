package ru.practicum.user;

import lombok.Data;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Data
public class UserDto {
    private String name;
    private String email;
    private String registrationDate;
    private String state;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd, HH:mm:ss");

    public static UserDto mapToUserDto(User user) {

        UserDto dto = new UserDto();
        dto.setName(user.getFullName());
        dto.setEmail(user.getEmail());
        // преобразуем registrationDate
        String formattedDate = user.getRegistrationDate()
                .atZone(ZoneId.systemDefault())
                .format(formatter);
        dto.setRegistrationDate(formattedDate);

        // преобразование enum
        dto.setState(user.getState().name());
        return dto;
    }
}