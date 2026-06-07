package cat.itacademy.s04.t01.userapi.service;

import cat.itacademy.s04.t01.userapi.exceptions.UserNotFoundException;
import cat.itacademy.s04.t01.userapi.models.User;
import cat.itacademy.s04.t01.userapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers(String name) {
        if (name == null) {
            return userRepository.findAll();
        }
        return userRepository.searchByName(name);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException());
    }
}
