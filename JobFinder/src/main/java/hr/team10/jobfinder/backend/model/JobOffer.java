package hr.team10.jobfinder.backend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_offers")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "employer_id", nullable = false)
    private User employer;


    private String title;

    @Column(length = 2000)
    private String description;

    private String location;

    private Double salary;

    private LocalDate jobDate;

    private LocalDateTime createdAt;
}
