package com.crabtree.hoyfc.model.customer;

import java.time.LocalDate;

public class EditCustomerParameters extends CreateCustomerParameters {

	public EditCustomerParameters(CustomerName userName, Gender gender, Email email, LocalDate birthday, PhoneNumber phoneNumber) {
		super(userName, gender, email, birthday, phoneNumber);
	}

	public void updateCustomer(Customer customer) {
		customer.setCustomerName(customer.getCustomerName());
		customer.setGender(customer.getGender());
		customer.setBirthday(getBirthday());
		customer.setEmail(getEmail());
		customer.setPhoneNumber(getPhoneNumber());
	}
}