package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUserRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();

    }

    @Override
    public List<User> searchByName(String name) {
        if (name == null) {
            return users;
        }

        return users.stream()
                .filter(user -> user.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    @Override
    public boolean existsByEmail(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
