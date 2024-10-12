package com.koi.FA24_SE1854_SWP391_G6_KoiCareHome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Fa24Se1854Swp391G6KoiCareHomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fa24Se1854Swp391G6KoiCareHomeApplication.class, args);
	}

}
