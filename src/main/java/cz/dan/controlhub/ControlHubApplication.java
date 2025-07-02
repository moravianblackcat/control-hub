package cz.dan.controlhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cz.dan")
public class ControlHubApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControlHubApplication.class, args);
    }

}
