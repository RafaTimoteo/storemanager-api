package br.com.storemanager.storemanagerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication( exclude = {SecurityAutoConfiguration.class})
public class StoremanagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoremanagerApiApplication.class, args);
	}

}
