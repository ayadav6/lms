package edu.depaul.cdm.se452.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"edu.depaul.cdm.se452"})
@EnableJpaRepositories(basePackages = "edu.depaul.cdm.se452.user")
@EntityScan(basePackages = {"edu.depaul.cdm.se452.user", "edu.depaul.cdm.se452.course", "edu.depaul.cdm.se452.assessment", "edu.depaul.cdm.se452.discussionforum", "edu.depaul.cdm.se452.notification"}) // Explicitly scan both User and Course entities
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
