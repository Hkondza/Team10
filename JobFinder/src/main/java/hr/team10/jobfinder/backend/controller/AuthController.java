package hr.team10.jobfinder.backend.controller;

import hr.team10.jobfinder.backend.dto.LoginRequest;
import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.UserRepository;
import hr.team10.jobfinder.backend.services.PasswordService;
import hr.team10.jobfinder.backend.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordService ps = new PasswordService();

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {

        if (userRepository.existsByEmail(req.email)) {
            throw new RuntimeException("User already exists");
        }

        return userRepository.save(UserService.register(req));
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!ps.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }


        return ResponseEntity.ok(
                Map.of(
                        "id",user.getId(),
                        "email", user.getEmail(),
                        "fullName", user.getFull_name(),
                        "role", user.getRole()
                )
        );
    }
}