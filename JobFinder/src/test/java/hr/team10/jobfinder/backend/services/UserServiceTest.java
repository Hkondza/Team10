package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordService passwordService;

    @InjectMocks
    private UserService userService;

    @Test
    void register_shouldExecuteWithoutError_whenEmailIsNew() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("new@mail.com");
        request.setPassword("123");


        assertDoesNotThrow(() -> userService.register(request));

    }


    @Test
    void register_shouldExecuteWithoutCrash() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("new@mail.com");

        assertDoesNotThrow(() -> userService.register(request));
    }

}
