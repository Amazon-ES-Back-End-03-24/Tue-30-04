package com.ironhack.tue3004.repository;

import com.ironhack.tue3004.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;
    User user2;

    @BeforeEach
    void setUp() {
        user = new User("John Doe", "john.doe@exmaple.com");
        user2 = new User("Jane Doe", "jane.doe@exmaple.com");
        userRepository.saveAll(List.of(user, user2));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void findByNameOk() {
        // query we are testing:
        User result = userRepository.findByName("John Doe");

        assertEquals("john.doe@exmaple.com", result.getEmail());
    }

    @Test
    public void findByEmailOK() {
        User user3 = new User("Juana de Arco", "jane.doe@exmaple.com");
        userRepository.save(user3);

        // query we are testing:
        List<User> result = userRepository.findByEmail("jane.doe@exmaple.com");

        assertEquals(2, result.size());
    }
}