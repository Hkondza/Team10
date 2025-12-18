package hr.team10.jobfinder.backend.controller;

import hr.team10.jobfinder.backend.model.JobApplication;
import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import hr.team10.jobfinder.backend.services.JobApplicationService;
import hr.team10.jobfinder.backend.services.JobOfferService;
import hr.team10.jobfinder.backend.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;
    private final UserService userService;
    private final JobOfferService jobOfferService;

    public JobApplicationController(
            JobApplicationService jobApplicationService,
            UserService userService,
            JobOfferService jobOfferService
    ) {
        this.jobApplicationService = jobApplicationService;
        this.userService = userService;
        this.jobOfferService = jobOfferService;
    }

    // Student se prijavljuje na oglas
    @PostMapping("/apply")
    public JobApplication apply(
            @RequestParam Long studentId,
            @RequestParam Long jobOfferId
    ) {
        User student = userService.getById(studentId);
        JobOffer jobOffer = jobOfferService.getAllJobs()
                .stream()
                .filter(j -> j.getId().equals(jobOfferId))
                .findFirst()
                .orElseThrow();

        return jobApplicationService.apply(student, jobOffer);
    }
}
