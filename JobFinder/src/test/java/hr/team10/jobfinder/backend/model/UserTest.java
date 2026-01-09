package hr.team10.jobfinder.backend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUserAndSetFields() {
        User user = new User();

        user.setEmail("test@mail.com");
        user.setFull_name("Hrvoje");
        user.setPassword("secret");
        user.setRole("student");

        assertEquals("test@mail.com", user.getEmail());
        assertEquals("Hrvoje", user.getFull_name());
        assertEquals("secret", user.getPassword());
        assertEquals("student", user.getRole());
    }
}