package com.azure;

import com.azure.user.entity.User;
import com.azure.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class AzureDemoApplication implements CommandLineRunner {

	private final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AzureDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userService.create(User.builder().name("Rupesh Dulal").email("dulalrupesh77@gmail.com").build());
	}
}
