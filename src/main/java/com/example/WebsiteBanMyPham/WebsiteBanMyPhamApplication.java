package com.example.WebsiteBanMyPham;

import com.example.WebsiteBanMyPham.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsiteBanMyPhamApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebsiteBanMyPhamApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {

			storageService.init();
		};
	}

}
