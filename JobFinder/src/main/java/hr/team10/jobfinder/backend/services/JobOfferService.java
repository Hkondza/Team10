package hr.team10.jobfinder.backend.services;

import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.JobOfferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public JobOffer createJobOffer(JobOffer jobOffer, User employer) {
        jobOffer.setEmployer(employer);
        jobOffer.setCreatedAt(LocalDateTime.now());
        return jobOfferRepository.save(jobOffer);
    }

    public List<JobOffer> getAllJobs() {
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> getJobsByEmployer(User employer) {
        return jobOfferRepository.findByEmployer(employer);
    }
}