package hr.team10.jobfinder.backend.controller;

import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.repo.UserRepository;
import hr.team10.jobfinder.backend.services.JobOfferService;
import hr.team10.jobfinder.backend.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class JobOfferController {

    private final JobOfferService jobOfferService;
    private final UserRepository userRepository;

    public JobOfferController(JobOfferService jobOfferService, UserService userService, UserRepository userRepository) {
        this.jobOfferService = jobOfferService;
        this.userRepository = userRepository;
    }

    // Employer kreira oglas
    @PostMapping("/{employerId}")
    public JobOffer createJob(
            @PathVariable Long employerId,
            @RequestBody JobOffer jobOffer
    ) {
        User employer = userRepository.getById(employerId);
        return jobOfferService.createJobOffer(jobOffer, employer);
    }

    // Svi oglasi
    @GetMapping
    public List<JobOffer> getAllJobs() {
        return jobOfferService.getAllJobs();
    }

    @GetMapping("/employer/{employerId}")
    public List<JobOffer> getAllJobsOfUser(@PathVariable Long employerId)
    {
        return jobOfferService.getJobsByEmployer(userRepository.getUserById(employerId));
    }
}