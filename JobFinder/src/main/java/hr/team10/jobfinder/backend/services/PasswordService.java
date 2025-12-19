package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.interfaces.PasswordEncoderService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService implements PasswordEncoderService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String  hashPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public boolean matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}

