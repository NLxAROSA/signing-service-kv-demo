package com.vmware.lars.signing.signingdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner signAndValidate(SigningService signingService)	{

		return args ->	{
			String message = "Hello signing world!";
			byte[] signature = signingService.signMessage(message);
			Boolean isValidSignature = signingService.isValidSignature(message, signature);
			LOGGER.info("Valid signature? (YES) ->" + isValidSignature);
			Boolean isNotValidSignature = signingService.isValidSignature(message, "thisisnotasignature".getBytes("UTF-8"));
			LOGGER.info("Valid signature? (NO) -> " + isNotValidSignature);
		};

	}

}
