package hr.team10.jobfinder.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.team10.jobfinder.backend.dto.LoginRequest;
import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_shouldReturnOk() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@mail.com");
        request.setPassword("1234");
        request.setFullName("Test User");
        request.setRole("student");

        doNothing().when(userService).register(request);

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void login_shouldReturnOk() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@mail.com");
        request.setPassword("1234");

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}