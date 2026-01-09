package hr.team10.jobfinder.backend.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordServiceTest {

    private final PasswordService passwordService = new PasswordService();

    @Test
    void encode_shouldReturnEncodedPassword() {
        String rawPassword = "12345";

        String encoded = passwordService.encode(rawPassword);

        assertNotNull(encoded);
        assertNotEquals(rawPassword, encoded);
    }

    @Test
    void matches_shouldReturnTrue_forCorrectPassword() {
        String rawPassword = "password";
        String encoded = passwordService.encode(rawPassword);

        boolean matches = passwordService.matches(rawPassword, encoded);

        assertTrue(matches);
    }

    @Test
    void matches_shouldReturnFalse_forWrongPassword() {
        String encoded = passwordService.encode("password");

        boolean matches = passwordService.matches("wrong", encoded);

        assertFalse(matches);
    }
}