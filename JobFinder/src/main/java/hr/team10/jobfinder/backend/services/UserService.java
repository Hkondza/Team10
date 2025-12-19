package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.dto.RegisterRequest;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static PasswordService passwordService = new PasswordService();
    private final UserRepository userRepository;

    public UserService(PasswordService passwordService, UserRepository userRepository) {
        UserService.passwordService = passwordService;
        this.userRepository = userRepository;
    }

    public static User register(RegisterRequest request) {

        String hashed = passwordService.hashPassword(request.password);

        User user = new User(
                null,
                request.fullName,
                request.email,
                hashed,
                request.role
        );

        return user;
    }


    public User getById(Long studentId) {
        return userRepository.getOne(studentId);
    }

    public Optional<User> findByEmail(String email) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail;
    }
}

//Trenutacna verzija coda nekrsi OCR - princip jer nemamo u backundu nikakvu provjeru
// kojeg je user rola to se dogada u fontendu ali alipakcija nije dovoljno slozena da se to
// promjeni.
