package com.crabtree.hoyfc.config;

import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.github.javafaker.Faker;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Locale;

@Configuration
public class ApplicationConfiguration {

	@Bean
	public InsertionSort insertionSort() {
		return new InsertionSort();
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	@Primary
	public Faker faker() {
		return new Faker(new Locale("en-GB.yml"));
	}
}