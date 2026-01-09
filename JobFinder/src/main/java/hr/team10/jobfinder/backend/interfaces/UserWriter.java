package hr.team10.jobfinder.backend.interfaces;

import hr.team10.jobfinder.backend.model.User;

public interface UserWriter {
    User save(User user);
}
