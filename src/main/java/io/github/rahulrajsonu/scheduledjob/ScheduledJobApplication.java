package io.github.rahulrajsonu.scheduledjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScheduledJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduledJobApplication.class, args);
	}
}
