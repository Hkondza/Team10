package hr.team10.jobfinder.backend.dto;

import lombok.Getter;

@Getter
public class LoginRequest {
    public String email;
    public String password;
}
