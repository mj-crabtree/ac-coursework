package com.crabtree.hoyfc.model.customer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateCustomerFormData {
	private String firstName;
	private String lastName;
	private Gender gender;
	private String email;
	private LocalDate birthday;
	private String phoneNumber;

	public CreateCustomerParameters toParameters() {
		return new CreateCustomerParameters(
				new CustomerName(firstName, lastName),
				gender,
				new Email(email),
				birthday,
				new PhoneNumber(phoneNumber));
	}
}