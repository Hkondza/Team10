package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.JobOfferRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobOfferServiceTest {

    @Mock
    private JobOfferRepository jobOfferRepository;

    @InjectMocks
    private JobOfferService jobOfferService;

    @Test
    void createJobOffer_shouldSaveJobOffer() {
        User employer = new User();
        employer.setId(1L);

        JobOffer jobOffer = new JobOffer();
        jobOffer.setTitle("Java Developer");

        when(jobOfferRepository.save(any(JobOffer.class)))
                .thenReturn(jobOffer);

        JobOffer result = jobOfferService.createJobOffer(jobOffer, employer);

        assertNotNull(result);
        verify(jobOfferRepository).save(jobOffer);
    }

    @Test
    void getAllJobs_shouldReturnListOfJobs() {
        when(jobOfferRepository.findAll())
                .thenReturn(List.of(new JobOffer(), new JobOffer()));

        List<JobOffer> jobs = jobOfferService.getAllJobs();

        assertEquals(2, jobs.size());
        verify(jobOfferRepository).findAll();
    }

    @Test
    void getJobsByEmployer_shouldReturnJobs() {
        User employer = new User();
        employer.setId(1L);

        when(jobOfferRepository.findByEmployer(employer))
                .thenReturn(List.of(new JobOffer()));

        List<JobOffer> jobs = jobOfferService.getJobsByEmployer(employer);

        assertEquals(1, jobs.size());
        verify(jobOfferRepository).findByEmployer(employer);
    }
}