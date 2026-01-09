package hr.team10.jobfinder.backend.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JobApplicationTest {

    @Test
    void shouldSetAndGetFields() {
        User user = new User();
        JobOffer offer = new JobOffer();
        LocalDateTime now = LocalDateTime.now();

        JobApplication application = new JobApplication();
        application.setStudent(user);
        application.setJobOffer(offer);
        application.setStatus("PENDING");
        application.setAppliedAt(now);

        assertEquals(user, application.getStudent());
        assertEquals(offer, application.getJobOffer());
        assertEquals("PENDING", application.getStatus());
        assertEquals(now, application.getAppliedAt());
    }

    @Test
    void shouldCreateJobApplicationWithAllFields() {
        User student = new User();
        student.setEmail("student@mail.com");

        JobOffer offer = new JobOffer();
        offer.setTitle("Backend Developer");

        LocalDateTime appliedAt = LocalDateTime.now();

        JobApplication application = new JobApplication(
                1L,
                student,
                offer,
                appliedAt,
                "PENDING"
        );

        assertEquals(1L, application.getId());
        assertEquals(student, application.getStudent());
        assertEquals(offer, application.getJobOffer());
        assertEquals(appliedAt, application.getAppliedAt());
        assertEquals("PENDING", application.getStatus());
    }

}