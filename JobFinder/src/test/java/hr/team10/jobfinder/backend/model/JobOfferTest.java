package hr.team10.jobfinder.backend.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JobOfferTest {

    @Test
    void shouldSetAndGetFields() {
        JobOffer offer = new JobOffer();

        offer.setTitle("Java Developer");
        offer.setDescription("Spring Boot job");
        offer.setSalary(1500.00);

        assertEquals("Java Developer", offer.getTitle());
        assertEquals("Spring Boot job", offer.getDescription());
        assertEquals(1500, offer.getSalary());
    }


    @Test
    void shouldCreateJobOfferWithAllFields() {
        User employer = new User();
        employer.setEmail("employer@mail.com");

        LocalDate jobDate = LocalDate.now();
        LocalDateTime createdAt = LocalDateTime.now();

        JobOffer offer = new JobOffer(
                1L,
                employer,
                "Backend Developer",
                "Spring Boot job",
                "Zagreb",
                2000.0,
                jobDate,
                createdAt
        );

        assertEquals(1L, offer.getId());
        assertEquals(employer, offer.getEmployer());
        assertEquals("Backend Developer", offer.getTitle());
        assertEquals("Spring Boot job", offer.getDescription());
        assertEquals("Zagreb", offer.getLocation());
        assertEquals(2000.0, offer.getSalary());
        assertEquals(jobDate, offer.getJobDate());
        assertEquals(createdAt, offer.getCreatedAt());
    }

}