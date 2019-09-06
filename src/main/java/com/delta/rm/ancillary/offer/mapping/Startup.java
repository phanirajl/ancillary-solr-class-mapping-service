package com.delta.rm.ancillary.offer.mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
@ComponentScan({"com.delta.rm.ancillary.offer.mapping","com.delta.rm.ancillary.logger"})
public class Startup {

	public static void main(String[] args) {
		SpringApplication.run(Startup.class, args);
	}

}
