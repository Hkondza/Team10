package hr.team10.jobfinder.backend.model;


import lombok.Data;

@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role; // STUDENT or EMPLOYER

    public User() {}

    public User(Long id, String firstName, String lastName, String email, String password, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}

