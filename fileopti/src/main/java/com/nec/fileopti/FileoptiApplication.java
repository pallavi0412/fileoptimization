package com.nec.fileopti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FileoptiApplication {

  private static final Logger LOGGER = LoggerFactory.getLogger(FileoptiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FileoptiApplication.class, args);
	}


}
