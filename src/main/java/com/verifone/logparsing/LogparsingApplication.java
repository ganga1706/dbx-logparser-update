package com.verifone.logparsing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.verifone.logparsing.service.Read;
import com.verifone.logparsing.service.Write;

@SpringBootApplication
public class LogparsingApplication implements ApplicationRunner {
	@Autowired
	Read read;

	@Autowired
	Write write;

	@Autowired
	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(LogparsingApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		System.out.println("########################### Start #########################");
		read.dimeboxValidationTimeTakenFileWrite();
		write.csvwriter();
		System.out.println("########################### end #########################");
		System.exit(SpringApplication.exit(context));

	}

}
