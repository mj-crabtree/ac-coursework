package com.crabtree.hoyfc.model.customer.createCustomer;

import com.crabtree.hoyfc.model.customer.*;
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
	private String buildingNumber;
	private String firstLine;
	private String townCity;
	private String postalCode;
	private String country;

	public CreateCustomerParameters toParameters() {
		return new CreateCustomerParameters(
				new CustomerName(firstName, lastName),
				new Address(buildingNumber, firstLine, townCity, postalCode, country),
				gender,
				new Email(email),
				birthday,
				new PhoneNumber(phoneNumber));
	}
}