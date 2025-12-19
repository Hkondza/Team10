package hr.team10.jobfinder.backend.controller;

import hr.team10.jobfinder.backend.dto.LoginRequest;
import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.interfaces.PasswordEncoderService;
import hr.team10.jobfinder.backend.interfaces.UserReader;
import hr.team10.jobfinder.backend.interfaces.UserWriter;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.services.PasswordService;
import hr.team10.jobfinder.backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final UserReader userReader;
    private final UserWriter userWriter;
    private final PasswordEncoderService passwordService;
    public AuthController(UserService userService, UserReader userReader, UserWriter userWriter, PasswordEncoderService passwordService) {
        this.userService = userService;
        this.userReader = userReader;
        this.userWriter = userWriter;
        this.passwordService = passwordService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = UserService.register( new RegisterRequest(
                request.getEmail(),
                request.getFullName(),
                request.getPassword(),
                request.getRole())
        );

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        return userService.findByEmail(request.getEmail())
                .filter(user -> PasswordService.matches(
                        request.getPassword(),
                        user.getPassword()
                ))
                .map(user -> ResponseEntity.ok(
                        Map.of(
                                "id", user.getId(),
                                "email", user.getEmail(),
                                "fullName", user.getFull_name(),
                                "role", user.getRole()
                        )
                ))
                .orElse(ResponseEntity.status(401).build());
    }
}
