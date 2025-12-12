package hr.team10.jobfinder.backend.controller;

import hr.team10.jobfinder.backend.dto.LoginRequest;
import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.UserRepository;
import hr.team10.jobfinder.backend.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest req) {

        if (userRepository.existsByEmail(req.email)) {
            throw new RuntimeException("User already exists");
        }

        User user = new User(
                null,
                req.fullName,
                req.email,
                req.password,
                req.role
        );

        return userRepository.save(user);
    }

    // LOGIN
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest req) {

        User user = userRepository.findByEmail(req.email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(req.password)) {
            throw new RuntimeException("Wrong password");
        }

        return user;
    }
}
