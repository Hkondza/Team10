package hr.team10.jobfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobFinderApplication.class, args);

        try {
            // Works on ALL Windows setups
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler http://localhost:8080/login.html");
        } catch (Exception e) {
            System.out.println("Could not open browser: " + e.getMessage());
        }
    }
}
