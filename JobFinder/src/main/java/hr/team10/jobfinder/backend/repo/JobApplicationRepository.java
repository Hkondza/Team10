package hr.team10.jobfinder.backend.repo;

import hr.team10.jobfinder.backend.model.JobApplication;
import hr.team10.jobfinder.backend.model.JobOffer;
import hr.team10.jobfinder.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

    List<JobApplication> findByStudent(User student);

    List<JobApplication> findByJobOffer(JobOffer jobOffer);
}
