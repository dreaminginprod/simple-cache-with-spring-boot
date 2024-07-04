package com.dreaminginprod.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CacheWithBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CacheWithBootApplication.class, args);
	}

}
