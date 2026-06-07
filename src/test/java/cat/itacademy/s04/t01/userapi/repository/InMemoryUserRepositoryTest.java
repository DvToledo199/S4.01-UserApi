
package cat.itacademy.s04.t01.userapi.repository;

import cat.itacademy.s04.t01.userapi.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryUserRepositoryTest {

    private InMemoryUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
    }

    @Test
    void save_shouldStoreUser() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Juan Pérez");
        user.setEmail("juan@example.com");

        userRepository.save(user);

        assertEquals(1, userRepository.findAll().size());
    }

    @Test
    void findById_shouldReturnUserWhenExists() {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setName("Juan Pérez");
        user.setEmail("juan@example.com");

        userRepository.save(user);

        Optional<User> result = userRepository.findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void searchByName_shouldReturnMatchingUsers() {
        User user1 = new User();
        user1.setId(UUID.randomUUID());
        user1.setName("Juan Pérez");
        user1.setEmail("juan@example.com");

        User user2 = new User();
        user2.setId(UUID.randomUUID());
        user2.setName("María García");
        user2.setEmail("maria@example.com");

        userRepository.save(user1);
        userRepository.save(user2);

        assertEquals(1, userRepository.searchByName("juan").size());
    }

    @Test
    void existsByEmail_shouldReturnTrueWhenEmailExists() {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName("Juan Pérez");
        user.setEmail("juan@example.com");

        userRepository.save(user);

        assertTrue(userRepository.existsByEmail("juan@example.com"));
        assertFalse(userRepository.existsByEmail("otro@example.com"));
    }
}
