package com.crabtree.hoyfc.model.customer.editCustomer;

import com.crabtree.hoyfc.model.customer.*;
import com.crabtree.hoyfc.model.customer.createCustomer.CreateCustomerParameters;

import java.time.LocalDate;

public class EditCustomerParameters extends CreateCustomerParameters {

	public EditCustomerParameters(Address address, CustomerName userName, Gender gender, Email email, LocalDate birthday, PhoneNumber phoneNumber) {
		super(userName, address, gender, email, birthday, phoneNumber);
	}

	public void updateCustomer(Customer customer) {
		customer.setCustomerName(customer.getCustomerName());
		customer.setGender(customer.getGender());
		customer.setBirthday(getBirthday());
		customer.setEmail(getEmail());
		customer.setPhoneNumber(getPhoneNumber());
	}
}