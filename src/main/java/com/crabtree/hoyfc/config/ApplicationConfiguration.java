package com.crabtree.hoyfc.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Locale;

@Configuration
public class ApplicationConfiguration {

	@Bean
	@Primary
	public Faker faker() {
		return new Faker(new Locale("en-GB.yml"));
	}
}