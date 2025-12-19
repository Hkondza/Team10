package hr.team10.jobfinder.backend.model;

import hr.team10.jobfinder.backend.interfaces.ApplicationAction;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_applications")
public class JobApplication implements ApplicationAction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;


    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;


    private LocalDateTime appliedAt;

    // 📌 Status prijave (PENDING, ACCEPTED, REJECTED)
    private String status;

    @Override
    public void apply() {
        //trenutacno nije implementiran ali JobApplication može zamijeniti ApplicationAction
        // sto ce zadovoljeti LSP
    }
}
