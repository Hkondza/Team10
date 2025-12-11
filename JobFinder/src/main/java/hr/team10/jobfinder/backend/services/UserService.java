package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final PasswordService passwordService;
    private final List<User> users = new ArrayList<>();
    private Long idCounter = 1L;

    public UserService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public User register(RegisterRequest request) {

        String hashed = passwordService.hashPassword(request.password);

        User user = new User(
                idCounter++,
                request.firstName,
                request.lastName,
                request.email,
                hashed,
                request.role
        );

        users.add(user);
        return user;
    }

    public List<User> getAllUsers(){
        return users;
    }
}

