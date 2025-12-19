package hr.team10.jobfinder.backend.interfaces;

public interface PasswordEncoderService {
    String encode(String rawPassword);
    static boolean matches(String rawPassword, String encodedPassword);
}
