package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.model.JobApplication;
import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public JobApplication apply(User student, JobOffer jobOffer) {
        JobApplication application = new JobApplication();
        application.setStudent(student);
        application.setJobOffer(jobOffer);
        application.setAppliedAt(LocalDateTime.now());
        application.setStatus("PENDING");

        return jobApplicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsForJob(JobOffer jobOffer) {
        return jobApplicationRepository.findByJobOffer(jobOffer);
    }

    public List<JobApplication> getApplicationsByStudent(User student) {
        return jobApplicationRepository.findByStudent(student);
    }
}
