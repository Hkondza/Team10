package hr.team10.jobfinder.backend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    @Test
    void shouldSetAndGetFields() {
        RegisterRequest request = new RegisterRequest();

        request.setEmail("user@mail.com");
        request.setFullName("Test User");
        request.setPassword("password");
        request.setRole("student");

        assertEquals("user@mail.com", request.getEmail());
        assertEquals("Test User", request.getFullName());
        assertEquals("password", request.getPassword());
        assertEquals("student", request.getRole());
    }
}