package hr.team10.jobfinder.backend.controller;

import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request){
        return userService.register(request);
    }

    @GetMapping("/users")
    public Object getUsers(){
        return userService.getAllUsers();
    }
}
