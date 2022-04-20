package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication	
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan(basePackages="com.example")
@EnableScheduling
public class MongoPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoPracticeApplication.class, args);
	}

//	@Scheduled(fixedDelay = 1000)
	@Scheduled(cron = "* 2 * * * *")
	public void scheduleFixedDelayTask() {
		System.out.println("TimeSchedule triggered..");
		System.out.println("Fixed delay task - " + System.currentTimeMillis() / 1000);
	}

}
