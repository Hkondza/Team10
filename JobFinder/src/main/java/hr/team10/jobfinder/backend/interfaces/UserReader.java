package hr.team10.jobfinder.backend.interfaces;

import hr.team10.jobfinder.backend.model.User;

import java.util.Optional;

public interface UserReader {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long id);
}
