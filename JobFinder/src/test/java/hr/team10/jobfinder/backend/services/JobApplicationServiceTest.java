package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.model.JobApplication;
import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository jobApplicationRepository;

    @InjectMocks
    private JobApplicationService jobApplicationService;

    @Test
    void applyForJob_shouldCreateAndSaveApplication() {
        User student = new User();
        JobOffer jobOffer = new JobOffer();

        // repo će vratiti što god dobije
        when(jobApplicationRepository.save(any(JobApplication.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        JobApplication result = jobApplicationService.apply(student, jobOffer);

        // provjere
        assertEquals(student, result.getStudent());
        assertEquals(jobOffer, result.getJobOffer());
        assertEquals("PENDING", result.getStatus());
        assertNotNull(result.getAppliedAt());

        verify(jobApplicationRepository).save(any(JobApplication.class));
    }

    @Test
    void getApplicationsForJob_shouldReturnApplications() {
        JobOffer jobOffer = new JobOffer();

        when(jobApplicationRepository.findByJobOffer(jobOffer))
                .thenReturn(List.of(new JobApplication()));

        List<JobApplication> result =
                jobApplicationService.getApplicationsForJob(jobOffer);

        assertEquals(1, result.size());
        verify(jobApplicationRepository).findByJobOffer(jobOffer);
    }

    @Test
    void getApplicationsForStudent_shouldReturnApplications() {
        User student = new User();

        when(jobApplicationRepository.findByStudent(student))
                .thenReturn(List.of(new JobApplication(), new JobApplication()));

        List<JobApplication> result =
                jobApplicationService.getApplicationsByStudent(student);

        assertEquals(2, result.size());
        verify(jobApplicationRepository).findByStudent(student);
    }
}