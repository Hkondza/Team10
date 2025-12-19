package hr.team10.jobfinder.backend.dto;

import hr.team10.jobfinder.backend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegisterRequest {
    public String fullName;
    public String email;
    public String password;
    public String role; // STUDENT or EMPLOYER
}


