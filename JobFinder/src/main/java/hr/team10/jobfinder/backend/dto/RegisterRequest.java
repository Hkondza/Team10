package hr.team10.jobfinder.backend.dto;

import hr.team10.jobfinder.backend.model.Role;

public class RegisterRequest {

    public String fullName;
    public String email;
    public String password;
    public String role; // STUDENT or EMPLOYER
}
