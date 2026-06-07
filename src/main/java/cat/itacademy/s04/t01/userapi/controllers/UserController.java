package cat.itacademy.s04.t01.userapi.controllers;

import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")

    public List<User> getUsers(@RequestParam(required = false) String name) {

        return userService.getUsers(name);

    }

    @PostMapping("/users")

    public User createUser(@RequestBody User user) {

        return userService.createUser(user);

    }

    @GetMapping("/users/{id}")

    public User getUserId(@PathVariable UUID id) {

        return userService.getUserById(id);

    }


}
