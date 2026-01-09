package hr.team10.jobfinder.backend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void shouldReadPublicFieldsViaGetters() {
        LoginRequest request = new LoginRequest();

        // direktno postavljanje jer su polja public
        request.email = "test@mail.com";
        request.password = "123456";

        // getter dolazi iz Lomboka
        assertEquals("test@mail.com", request.getEmail());
        assertEquals("123456", request.getPassword());
    }
}