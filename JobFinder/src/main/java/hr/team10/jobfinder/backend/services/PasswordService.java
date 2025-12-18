package hr.team10.jobfinder.backend.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String  hashPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public static boolean  matches(String rawPassword, String hashedPassword) {
        return encoder.matches(rawPassword, hashedPassword);
    }
}

