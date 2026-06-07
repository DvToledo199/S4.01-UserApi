package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User user);

    List<User> getUsers(String name);

    User getUserById(UUID id);
}
