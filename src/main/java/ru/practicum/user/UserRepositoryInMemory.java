package ru.practicum.user;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryInMemory implements UserRepository {
    Map<Long, User> users = new HashMap<>();

    @NonFinal
    Long globalUserId = 0L;

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public User save(User user) {
        Long id = generateId();
        user.setId(id);
        users.put(id, user);
        return users.get(id);
    }

    private Long generateId() {
        return ++globalUserId;
    }
}