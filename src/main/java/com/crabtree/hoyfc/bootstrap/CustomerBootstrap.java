package com.crabtree.hoyfc.bootstrap;

import com.crabtree.hoyfc.model.customer.CustomerName;
import com.crabtree.hoyfc.model.customer.Email;
import com.crabtree.hoyfc.model.customer.Gender;
import com.crabtree.hoyfc.model.customer.PhoneNumber;
import com.crabtree.hoyfc.model.customer.createCustomer.CreateCustomerParameters;
import com.crabtree.hoyfc.service.CustomerService;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.remove;

@Component
@Order(1)
public class CustomerBootstrap implements CommandLineRunner {

	private final CustomerService customerService;
	private final Faker faker;

	public CustomerBootstrap(CustomerService customerService, Faker faker) {
		this.customerService = customerService;
		this.faker = faker;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World from customer");
		for (int i = 0; i < 100; i++) {
			CreateCustomerParameters parameters = newRandomUserParameters();
			customerService.createCustomer(parameters);
		}
	}

	private CreateCustomerParameters newRandomUserParameters() {
		Name name = faker.name();

		CustomerName userName = new CustomerName(name.firstName(), name.lastName());

		Gender gender = faker
				.bool()
				.bool() ? Gender.MALE : Gender.FEMALE;

		LocalDate birthday = LocalDate.ofInstant(faker
				.date()
				.birthday(18, 99)
				.toInstant(), ZoneId.systemDefault());

		Email email = new Email(faker
				.internet()
				.emailAddress(generateEmail(userName)));

		PhoneNumber phoneNumber = new PhoneNumber(faker
				.phoneNumber()
				.phoneNumber());

		return new CreateCustomerParameters(userName, gender, email, birthday, phoneNumber);
	}

	private String generateEmail(CustomerName userName) {
		return String.format("%s%s",
				remove(userName
						.getFirstName()
						.toLowerCase(Locale.ROOT), "''"),
				remove(userName
						.getLastName()
						.toLowerCase(Locale.ROOT), "'"));
	}
}